import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreModule } from '../core/core.module';
import { ConfigRoutingModule } from './config-routing.module';
import { UserComponent } from './user/user.component';
import { UserGroupComponent } from './user-group/user-group.component';

@NgModule({
    declarations: [
        UserComponent,
        UserGroupComponent
    ],
    imports: [
        CommonModule,
        CoreModule,
        ConfigRoutingModule
    ]
})
export class ConfigModule {
}
