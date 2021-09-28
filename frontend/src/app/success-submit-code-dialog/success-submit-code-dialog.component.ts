import { Component, OnInit } from '@angular/core';
import {NbDialogRef} from "@nebular/theme";

@Component({
  selector: 'app-success-submit-code-dialog',
  templateUrl: './success-submit-code-dialog.component.html',
  styleUrls: ['./success-submit-code-dialog.component.scss']
})
export class SuccessSubmitCodeDialogComponent implements OnInit {
  dialogRef: NbDialogRef<any>;

  constructor(dialogRef: NbDialogRef<any>) {
    this.dialogRef = dialogRef;
  }

  ngOnInit(): void {
  }

}
