import {Component, OnInit} from '@angular/core';
import {GridDto} from "../model/dto/grid-dto";
import {ApiService} from "./service/api.service";
import {Observable, of, Subject} from "rxjs";
import {concatMap, delay, map, reduce, takeUntil, tap} from "rxjs/operators";
import {flatMap} from "rxjs/internal/operators";
import {ResultDto} from "../model/dto/result-dto";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  titleText = ""
  currentGrid?: GridDto;
  resultPullingScheduleInterrupter: Subject<void>;
  activeGame?: ResultDto;

  constructor(private apiService: ApiService) {
    this.resultPullingScheduleInterrupter = new Subject<void>();
  }

  ngOnInit(): void {
    this.apiService.getResult().subscribe(resultDto => {
      this.updateGrid(resultDto.startGrid)
    })

    console.log("Schedule Pulling Result")
    this.schedulePullingResult()
  }

  schedulePullingResult() {
    console.log("Schedule Pulling Result")

    of("").pipe(takeUntil(this.resultPullingScheduleInterrupter), delay(1000)).subscribe(() => {
      this.apiService.getResult().pipe(takeUntil(this.resultPullingScheduleInterrupter)).subscribe(resultDto => {
        console.log("Pulling Result")
        if (JSON.stringify(this.activeGame) === JSON.stringify(resultDto)) {
          this.schedulePullingResult();
        } else {
          console.log("Active Game Changed");
          this.activeGame = resultDto;
          let hasTurns = resultDto.turns.length > 0;
          if (!hasTurns) {
            this.updateGrid(resultDto.startGrid);
            this.schedulePullingResult();
          } else {
            this.showTurns().subscribe(hasLastGrid => {
              if (resultDto.winner) {
                this.titleText = `🎈 We have a Winner: ${resultDto.winner}! 🥳`;
              } else {
                this.titleText = "🔗 It's a Tie! 💀"
              }
              this.schedulePullingResult();
            })
          }
        }
      }, error => this.schedulePullingResult());
    }, error => this.schedulePullingResult())
  }

  showTurns(): Observable<any> {
    return this.apiService.getResult()
      .pipe(
        map(value => value.turns),
        flatMap(value => value),
        concatMap(item => of(item).pipe(delay(100))),
        takeUntil(this.resultPullingScheduleInterrupter),
        tap(turnGrid => this.updateGrid(turnGrid)),
        reduce((acc, val) => (val.isLastGrid || acc), false))
  }

  private updateGrid(turnGrid: GridDto) {
    this.titleText = turnGrid.name;
    this.currentGrid = turnGrid;
  }

  onSubmitCode() {
    // Not used right now
    //this.schedulePullingResult();
  }

  onGameRoomChanged() {
    console.log("Game Room Changed")
    this.cancelResultPulling();
    this.schedulePullingResult();
  }

  private cancelResultPulling() {
    console.log("Reset Pulling")
    this.resultPullingScheduleInterrupter.next();
    this.resultPullingScheduleInterrupter.complete();
    this.resultPullingScheduleInterrupter = new Subject<void>();
  }
}
