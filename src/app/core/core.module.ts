import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDatepickerModule, MatExpansionModule } from '@angular/material';
import { CoreRoutingModule } from './core-routing.module';
import { RouterModule } from '@angular/router';
import { AgGridModule } from 'ag-grid-angular';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatMenuModule } from '@angular/material';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgxPermissionsModule } from 'ngx-permissions';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ErrorNessageComponent } from './error-nessage/error-nessage.component';
import { MenuComponent } from './menu/menu.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { PopupMenuComponent } from './popup-menu/popup-menu.component';
import { HistoricalDateComponent } from './historical-date/historical-date.component';
import { MatMomentDateModule } from '@angular/material-moment-adapter';
import { NgxMyDatePickerModule } from 'ngx-mydatepicker';
import { MultiColumnChoiceSelectorModalComponent } from './multi-column-choice-selector-modal/multi-column-choice-selector-modal.component';

@NgModule({
  declarations: [
    LoginComponent,
    HomeComponent,
    ErrorNessageComponent,
    MenuComponent,
    HeaderComponent,
    FooterComponent,
    PopupMenuComponent,
    HistoricalDateComponent,
    MultiColumnChoiceSelectorModalComponent
  ],
  imports: [
    AgGridModule,
    BrowserAnimationsModule,
    BrowserModule,
    CommonModule,
    FormsModule,
    HttpClientModule,
    MatDatepickerModule,
    MatExpansionModule,
    MatMenuModule,
    MatMomentDateModule,
    NgbModule,
    NgxMyDatePickerModule.forRoot(),
    NgxPermissionsModule.forRoot(),
    ReactiveFormsModule,
    CoreRoutingModule
  ],
  exports: [
    AgGridModule,
    FormsModule,
    HttpClientModule,
    MatDatepickerModule,
    MatExpansionModule,
    MatMomentDateModule,
    NgxMyDatePickerModule,
    NgxPermissionsModule,
    ReactiveFormsModule,
    RouterModule,
    ErrorNessageComponent,
    FooterComponent,
    HeaderComponent,
    HomeComponent,
    LoginComponent,
    MenuComponent,
    NgbModule,
    PopupMenuComponent,
    HistoricalDateComponent
  ]
})
export class CoreModule { }
