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
import { DeityTypeComponent } from './deity-type/deity-type.component';
import { EntityTypeComponent } from './entity-type/entity-type.component';
import { EventTypeGroupComponent } from './event-type-group/event-type-group.component';
import { HierarchyTypeComponent } from './hierarchy-type/hierarchy-type.component';

@NgModule({
  declarations: [
    SimpleRefdataComponent,
    AddressTypeComponent,
    ArtefactGroupComponent,
    ArtefactTypeComponent,
    CalendarTypeComponent,
    DeityTypeComponent,
    EntityTypeComponent,
    EventTypeGroupComponent,
    HierarchyTypeComponent,
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
