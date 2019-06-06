import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReferenceDataRoutingModule } from './refdata-routing.module';
import { SimpleRefdataComponent } from './simple-refdata/simple-refdata.component';
import { CoreModule } from '../core/core.module';

@NgModule({
  declarations: [SimpleRefdataComponent],
  imports: [
    CommonModule,
    CoreModule,
    ReferenceDataRoutingModule
  ]
})
export class ReferenceDataModule { }
