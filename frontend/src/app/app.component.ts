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
  display = true;
  currentGrid?: GridDto;

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
    this.apiService.getResult().subscribe(resultDto => {
      this.currentGrid = resultDto.startGrid;
    })

    console.log("Schedule Pulling Result")
    this.schedulePullingResult()
  }

  schedulePullingResult() {
    of("").pipe(delay(1000)).subscribe(() => {
      this.updateGrid().subscribe(finishedGame => {
        if (!finishedGame){
          this.schedulePullingResult()
        }
      });
    })
  }

  updateGrid(): Observable<any> {
    return this.apiService.getResult()
      .pipe(
        map(value => value.turns),
        flatMap(value => value),
        concatMap(item => of(item).pipe(delay(100))),
        tap(turnGrid => this.currentGrid = turnGrid),
        reduce((acc, val) => (val.isLastGrid || acc), false))
  }

  timeout(timeoutDuration: number) {
    return new Promise(resolve => setTimeout(resolve, timeoutDuration));
  }

  onSubmitCode() {
    this.schedulePullingResult();
  }
}
