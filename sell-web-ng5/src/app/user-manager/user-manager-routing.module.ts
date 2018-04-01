import {NgModule} from "@angular/core";
import {Route, RouterModule} from "@angular/router";
import {UserListComponent} from "./user-list/user-list.component";

const routes: Route[] = [
  {path: '', redirectTo: 'list'},
  {path: 'list', component: UserListComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserManagerRoutingModule {
}
