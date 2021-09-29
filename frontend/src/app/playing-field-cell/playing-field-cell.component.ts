import {Component, Input, OnInit} from '@angular/core';
import {FieldDto} from "../../model/dto/field-dto";

@Component({
  selector: 'app-playing-field-cell',
  templateUrl: './playing-field-cell.component.html',
  styleUrls: ['./playing-field-cell.component.scss']
})
export class PlayingFieldCellComponent implements OnInit {
  private iconsMap = {
    'PLAYER': 'person-outline',
    'FLAG': 'flag-outline',
    'BORDER': 'cube-outline'
  }

  @Input()
  field?: FieldDto;

  constructor() {
  }

  ngOnInit(): void {
  }

  getIconForFieldType(fieldType: string): string{
    // @ts-ignore
    return this.iconsMap[fieldType];
  }
}
