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
import { ImageTypeComponent } from './image-type/image-type.component';
import { LanguageGroupComponent } from './language-group/language-group.component';
import { MeasureTypeComponent } from './measure-type/measure-type.component';
import { PersonTypeComponent } from './person-type/person-type.component';
import { PublicationTypeComponent } from './publication-type/publication-type.component';
import { RaceTypeComponent } from './race-type/race-type.component';
import { TaleTypeComponent } from './tale-type/tale-type.component';
import { TechnologyTypeGroupComponent } from './technology-type-group/technology-type-group.component';

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
    ImageTypeComponent,
    LanguageGroupComponent,
    MeasureTypeComponent,
    PersonTypeComponent,
    PublicationTypeComponent,
    RaceTypeComponent,
    TaleTypeComponent,
    TechnologyTypeGroupComponent,
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
