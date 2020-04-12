import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ResearchRoutingModule } from './research-routing.module';
import { AgGridModule } from 'ag-grid-angular';
import { PersonComponent } from './person/person.component';
import { CoreModule } from '../core/core.module';
import { TitlesComponent } from './titles/titles.component';
import { UserDetailComponent } from '../config/user-detail/user-detail.component';

@NgModule({
  declarations: [TitlesComponent, UserDetailComponent],
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
