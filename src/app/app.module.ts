import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import {ReferenceDataModule} from './refdata/refdata.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    CoreModule,
    ReferenceDataModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
