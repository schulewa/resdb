import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReferenceDataRoutingModule } from './refdata-routing.module';
import { SimpleRefdataComponent } from './simple-refdata/simple-refdata.component';

@NgModule({
  declarations: [SimpleRefdataComponent],
  imports: [
    CommonModule,
    ReferenceDataRoutingModule
  ]
})
export class ReferenceDataModule { }
