import {NgModule} from "@angular/core";
import {Route, RouterModule} from "@angular/router";
import {StatisticQueryComponent} from "./statistic-query/statistic-query.component";

const routes: Route[] = [
  {path: '', redirectTo: 'list'},
  {path: 'list', component: StatisticQueryComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StatisticManagerRoutingModule {
}
