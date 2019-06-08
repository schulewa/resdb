import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReferenceDataRoutingModule } from './refdata-routing.module';
import { SimpleRefdataComponent } from './simple-refdata/simple-refdata.component';
import { CoreModule } from '../core/core.module';
import { AddressTypeComponent } from './address-type/address-type.component';
import { AgGridModule } from 'ag-grid-angular';

@NgModule({
  declarations: [SimpleRefdataComponent, AddressTypeComponent],
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
