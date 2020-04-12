import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SimpleRefdataComponent } from './simple-refdata/simple-refdata.component';
import {CountryComponent} from './countries/country.component';
import {PlaceComponent} from './place/place.component';

const routes: Routes = [
  { path: 'countries', component: CountryComponent },
  { path: 'places', component: PlaceComponent },
  { path: 'simple-refdata', component: SimpleRefdataComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReferenceDataRoutingModule { }
