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
  }
}
