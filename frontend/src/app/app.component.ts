import {Component, OnInit} from '@angular/core';
import {GridDto} from "../model/dto/grid-dto";
import {ApiService} from "./service/api.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
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

  schedulePullingResult(){
    setTimeout(() => {
      this.updateGrid();
      this.schedulePullingResult()
    }, 1000)
  }

  async updateGrid() {
    // todo
    console.log("Pull result")
    let resultDto = await this.apiService.getResult().toPromise();
    if(resultDto.turns){
      for (const turnGrid of resultDto.turns) {
        this.currentGrid = turnGrid;
        await this.timeout(1000);
      }
    }
  }

  timeout(timeoutDuration: number) {
    return new Promise(resolve => setTimeout(resolve, timeoutDuration));
  }
}
