import {NgModule} from "@angular/core";
import {Route, RouterModule} from "@angular/router";
import {ShopListQueryComponent} from "./shop-list-query/shop-list-query.component";
import {OfflineShopListQueryComponent} from "./offline-shop-list-query/offline-shop-list-query.component";

const routes: Route[] = [
  {path: '', redirectTo: 'list'},
  {path: 'list', component: ShopListQueryComponent},
  {path: 'offline-list', component: OfflineShopListQueryComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ShopManagerRoutingModule {
}
