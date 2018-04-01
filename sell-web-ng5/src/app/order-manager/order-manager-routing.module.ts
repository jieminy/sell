import {NgModule} from "@angular/core";
import {Route, RouterModule} from "@angular/router";
import {OrderListQueryComponent} from "./order-list-query/order-list-query.component";
import {HistoryOrderListQueryComponent} from "./history-order-list-query/history-order-list-query.component";

const routes: Route[] = [
  {path: '', redirectTo: 'list'},
  {path: 'list', component: OrderListQueryComponent},
  {path: 'history-list', component: HistoryOrderListQueryComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrderManagerRoutingModule {
}
