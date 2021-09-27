import {Component, Inject, Input, OnInit} from '@angular/core';
import {GridDto} from "../../model/dto/grid-dto";

@Component({
  selector: 'app-playing-field',
  templateUrl: './playing-field.component.html',
  styleUrls: ['./playing-field.component.scss']
})
export class PlayingFieldComponent implements OnInit {

  @Input()
  grid?: GridDto;

  constructor() {}

  ngOnInit(): void {
  }

}
