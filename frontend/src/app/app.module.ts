import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  NbThemeModule,
  NbLayoutModule,
  NbCardModule,
  NbSpinnerModule,
  NbIconModule,
  NbButtonModule, NbDialogModule, NbInputModule
} from '@nebular/theme';
import { NbEvaIconsModule } from '@nebular/eva-icons';
import { PlayingFieldComponent } from './playing-field/playing-field.component';
import { PlayingFieldCellComponent } from './playing-field-cell/playing-field-cell.component';
import { EditorComponent } from './editor/editor.component';
import { SuccessSubmitCodeDialogComponent } from './success-submit-code-dialog/success-submit-code-dialog.component';
import { FailedSubmitCodeDialogComponent } from './failed-submit-code-dialog/failed-submit-code-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    PlayingFieldComponent,
    PlayingFieldCellComponent,
    EditorComponent,
    SuccessSubmitCodeDialogComponent,
    FailedSubmitCodeDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NbThemeModule.forRoot({name: 'cosmic'}),
    NbLayoutModule,
    NbEvaIconsModule,
    NbCardModule,
    NbSpinnerModule,
    NbIconModule,
    NbButtonModule,
    NbDialogModule.forRoot(),
    NbInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
