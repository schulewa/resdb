import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

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

@NgModule({
  declarations: [
    LoginComponent,
    HomeComponent,
    ErrorNessageComponent,
    MenuComponent,
    HeaderComponent,
    FooterComponent,
    PopupMenuComponent
  ],
  imports: [
    AgGridModule,
    BrowserAnimationsModule,
    BrowserModule,
    CommonModule,
    FormsModule,
    HttpClientModule,
    MatMenuModule,
    NgbModule,
    NgxPermissionsModule.forRoot(),
    ReactiveFormsModule,
    CoreRoutingModule
  ],
  exports: [
    AgGridModule,
    FormsModule,
    HttpClientModule,
    NgxPermissionsModule,
    ReactiveFormsModule,
    RouterModule,
    FooterComponent,
    HeaderComponent,
    HomeComponent,
    LoginComponent,
    MenuComponent,
    PopupMenuComponent
  ]
})
export class CoreModule { }
