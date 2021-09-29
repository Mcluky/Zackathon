import {Component, OnInit} from '@angular/core';
import {GridDto} from "../model/dto/grid-dto";
import {ApiService} from "./service/api.service";
import {Observable, of} from "rxjs";
import {concatMap, delay, map, reduce, tap} from "rxjs/operators";
import {flatMap} from "rxjs/internal/operators";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  titleText = ""
  currentGrid?: GridDto;

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
    this.apiService.getResult().subscribe(resultDto => {
      this.currentGrid = resultDto.startGrid;
      this.titleText = this.currentGrid.name;
    })

    console.log("Schedule Pulling Result")
    this.schedulePullingResult()
  }

  schedulePullingResult() {
    of("").pipe(delay(1000)).subscribe(() => {
      this.updateGrid().subscribe(finishedGame => {
        this.apiService.getResult().subscribe(resultDto => {
          if(finishedGame){
            if(resultDto.winner){
              this.titleText = `🎈 We have a Winner: ${resultDto.winner}! 🥳`;
            }
            this.titleText = "🔗 It's a Tie! 💀"
          } else {
            this.apiService.getResult().subscribe(resultDto => {
              this.currentGrid = resultDto.startGrid;
              this.schedulePullingResult()
            })
          }
        })
      });
    })
  }

  updateGrid(): Observable<any> {
    return this.apiService.getResult()
      .pipe(
        map(value => value.turns),
        flatMap(value => value),
        concatMap(item => of(item).pipe(delay(100))),
        tap(turnGrid => {
          this.titleText = turnGrid.name;
          return this.currentGrid = turnGrid;
        }),
        reduce((acc, val) => (val.isLastGrid || acc), false))
  }

  timeout(timeoutDuration: number) {
    return new Promise(resolve => setTimeout(resolve, timeoutDuration));
  }

  onSubmitCode() {
    this.schedulePullingResult();
  }
}
