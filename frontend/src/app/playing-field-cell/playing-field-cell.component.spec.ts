import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlayingFieldCellComponent } from './playing-field-cell.component';

describe('PlayingFieldCellComponent', () => {
  let component: PlayingFieldCellComponent;
  let fixture: ComponentFixture<PlayingFieldCellComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlayingFieldCellComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlayingFieldCellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
