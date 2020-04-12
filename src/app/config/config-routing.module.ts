import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserComponent } from './user/user.component';
import { UserGroupComponent } from './user-group/user-group.component';

const routes: Routes = [
    {path: 'users', component: UserComponent},
    {path: 'usergroups', component: UserGroupComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ConfigRoutingModule {
}
