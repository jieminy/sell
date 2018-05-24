import {NgModule} from "@angular/core";
import {Route, RouterModule} from "@angular/router";
import {AdvListQueryComponent} from "./adv-list-query/adv-list-query.component";

const routes: Route[] = [
  {path: '', redirectTo: 'list'},
  {path: 'list', component: AdvListQueryComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdvProductManagerRoutingModule {
}
