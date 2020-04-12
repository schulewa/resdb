import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PersonComponent } from './person/person.component';
import {TitlesComponent} from './titles/titles.component';
import {PersonDetailComponent} from './person-detail/person-detail.component';

const routes: Routes = [
  { path: 'persons', component: PersonComponent },
  { path: 'addperson', component: PersonDetailComponent },
  { path: 'titles', component: TitlesComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ResearchRoutingModule { }
