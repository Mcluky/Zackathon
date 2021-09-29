import {Component, ElementRef, EventEmitter, HostListener, OnInit, Output, ViewChild} from '@angular/core';
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

  @Output()
  onSubmitCode: EventEmitter<any> = new EventEmitter();

  @Output()
  onGameRoomChanged: EventEmitter<string> = new EventEmitter();


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
      this.onSubmitCode.emit();
    }, error => {
      console.error(error);
      this.dialogService.open(FailedSubmitCodeDialogComponent);
    });
  }

  onUsernameChanged($event: Event) {
    // @ts-ignore
    this.parameterService.userName = $event.target!.value;
  }

  onGameRoomNameChanged($event: KeyboardEvent) {
    // @ts-ignore
    this.parameterService.gameRoom = $event.target!.value;
    this.onGameRoomChanged.emit(this.parameterService.gameRoom);
  }

  resetGameRoom() {
    this.apiService.resetGameRoom().subscribe(value => value);
    this.onGameRoomChanged.emit(this.parameterService.gameRoom);
  }
}
