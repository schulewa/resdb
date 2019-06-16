import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ResearchRoutingModule } from './research-routing.module';
import { AgGridModule } from 'ag-grid-angular';
import { PersonComponent } from './person/person.component';
import { CoreModule } from '../core/core.module';

@NgModule({
  declarations: [],
  imports: [
    AgGridModule.withComponents([
      PersonComponent
    ]),
    CommonModule,
    CoreModule,
    ResearchRoutingModule
  ]
})
export class ResearchModule { }