import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { ReferenceDataModule } from './refdata/refdata.module';
import { PersonComponent } from './research/person/person.component';
import { PersonDetailComponent } from './research/person-detail/person-detail.component';
import {ResearchModule} from './research/research.module';

@NgModule({
  declarations: [
    AppComponent,
    PersonComponent,
    PersonDetailComponent
  ],
  imports: [
    BrowserModule,
    CoreModule,
    ReferenceDataModule,
    ResearchModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
