import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FailedSubmitCodeDialogComponent } from './failed-submit-code-dialog.component';

describe('FailedSubmitCodeDialogComponent', () => {
  let component: FailedSubmitCodeDialogComponent;
  let fixture: ComponentFixture<FailedSubmitCodeDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FailedSubmitCodeDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FailedSubmitCodeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
