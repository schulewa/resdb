import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReferenceDataRoutingModule } from './refdata-routing.module';
import { SimpleRefdataComponent } from './simple-refdata/simple-refdata.component';
import { CoreModule } from '../core/core.module';
import { AddressTypeComponent } from './address-type/address-type.component';
import { AgGridModule } from 'ag-grid-angular';
import { ArtefactGroupComponent } from './artefact-group/artefact-group.component';
import { ArtefactTypeComponent } from './artefact-type/artefact-type.component';
import { CalendarTypeComponent } from './calendar-type/calendar-type.component';

@NgModule({
  declarations: [
    SimpleRefdataComponent,
    AddressTypeComponent,
    ArtefactGroupComponent,
    ArtefactTypeComponent,
    CalendarTypeComponent,
  ],
  imports: [
    AgGridModule.withComponents([
      AddressTypeComponent
    ]),
    CommonModule,
    CoreModule,
    ReferenceDataRoutingModule
  ]
})
export class ReferenceDataModule { }
