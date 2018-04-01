import {NgModule} from '@angular/core';
import {SharedModule} from "../shared/shared.module";
import {UserManagerRoutingModule} from "./user-manager-routing.module";
import {UserListComponent} from './user-list/user-list.component';
import {UserService} from "./user.service";
import {UserEditComponent} from './user-list/user-edit/user-edit.component';

@NgModule({
  imports: [
    SharedModule,
    UserManagerRoutingModule
  ],
  declarations: [UserListComponent, UserEditComponent],
  providers: [UserService],
  entryComponents: [UserEditComponent]
})
export class UserManagerModule {
}
