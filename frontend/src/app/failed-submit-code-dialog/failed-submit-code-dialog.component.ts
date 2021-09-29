import { Component, OnInit } from '@angular/core';
import {NbDialogRef} from "@nebular/theme";

@Component({
  selector: 'app-failed-submit-code-dialog',
  templateUrl: './failed-submit-code-dialog.component.html',
  styleUrls: ['./failed-submit-code-dialog.component.scss']
})
export class FailedSubmitCodeDialogComponent implements OnInit {
  dialogRef: NbDialogRef<any>;

  constructor(dialogRef: NbDialogRef<any>) {
    this.dialogRef = dialogRef;
  }

  ngOnInit(): void {
  }

}
