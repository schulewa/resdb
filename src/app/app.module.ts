import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { ReferenceDataModule } from './refdata/refdata.module';
import { PersonComponent } from './research/person/person.component';
import { PersonDetailComponent } from './research/person-detail/person-detail.component';
import { ResearchModule } from './research/research.module';
import {NgbDatepickerModule} from '@ng-bootstrap/ng-bootstrap';
import {ConfigModule} from './config/config.module';
// import {MatDatepickerModule, MatExpansionModule} from '@angular/material';

@NgModule({
  declarations: [
    AppComponent,
    PersonComponent,
    PersonDetailComponent
  ],
  imports: [
    BrowserModule,
    CoreModule,
    ConfigModule,
    ReferenceDataModule,
    ResearchModule,
    NgbDatepickerModule,
    // MatExpansionModule,
    // MatDatepickerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
