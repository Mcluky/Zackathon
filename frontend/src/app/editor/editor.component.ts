import {Component, ElementRef, HostListener, OnInit, ViewChild} from '@angular/core';
import {editor} from "monaco-editor";
import IStandaloneCodeEditor = editor.IStandaloneCodeEditor;
import {ApiService} from "../service/api.service";
import {NbDialogService} from "@nebular/theme";
import {SuccessSubmitCodeDialogComponent} from "../success-submit-code-dialog/success-submit-code-dialog.component";
import {FailedSubmitCodeDialogComponent} from "../failed-submit-code-dialog/failed-submit-code-dialog.component";
import {ParameterService} from "../service/parameter.service";

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.scss']
})
export class EditorComponent implements OnInit {

  @ViewChild('vsCodeEditor')
  vsCodeEditor?: ElementRef;

  constructor(private apiService: ApiService, private dialogService: NbDialogService,
              public parameterService: ParameterService) {
  }

  ngOnInit(): void {
  }

  getVsCodeEditor(): IStandaloneCodeEditor {
    // @ts-ignore --> defined in index.html
    return window.vsCodeEditor as IStandaloneCodeEditor;
  }

  submitCode() {
    let code = this.getVsCodeEditor().getValue();
    console.log("Sending Code: " + code)
    this.apiService.sendCode(code).subscribe(_ => {
      this.dialogService.open(SuccessSubmitCodeDialogComponent);
    }, error => {
      console.error(error);
      this.dialogService.open(FailedSubmitCodeDialogComponent);
    });
  }

  onUsernameChanged($event: Event) {
    // @ts-ignore
    this.parameterService.userName = $event.target!.value;
  }

  onGameRoomChanged($event: KeyboardEvent) {
    // @ts-ignore
    this.parameterService.gameRoom = $event.target!.value;
  }
}
