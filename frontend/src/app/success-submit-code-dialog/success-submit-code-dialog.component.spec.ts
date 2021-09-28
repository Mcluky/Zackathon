import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessSubmitCodeDialogComponent } from './success-submit-code-dialog.component';

describe('SuccessSubmitCodeDialogComponent', () => {
  let component: SuccessSubmitCodeDialogComponent;
  let fixture: ComponentFixture<SuccessSubmitCodeDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuccessSubmitCodeDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SuccessSubmitCodeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
