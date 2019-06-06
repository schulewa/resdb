import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SimpleRefdataComponent } from './simple-refdata/simple-refdata.component';

const routes: Routes = [
  { path: 'simple-refdata', component: SimpleRefdataComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReferenceDataRoutingModule { }
