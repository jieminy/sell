import {NgModule} from "@angular/core";
import {Route, RouterModule} from "@angular/router";
import {ActivityListQueryComponent} from "./activity-list-query/activity-list-query.component";

const routes: Route[] = [
  {path: '', redirectTo: 'list'},
  {path: 'list', component: ActivityListQueryComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ActivityManagerRoutingModule {
}
