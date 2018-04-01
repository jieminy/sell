import {NgModule} from '@angular/core';
import {SharedModule} from "../shared/shared.module";
import {ShopManagerRoutingModule} from "./shop-manager-routing.module";
import {ShopListQueryComponent} from './shop-list-query/shop-list-query.component';
import {OfflineShopListQueryComponent} from './offline-shop-list-query/offline-shop-list-query.component';
import {ShopService} from "./shop.service";
import {ShopEditComponent} from './offline-shop-list-query/shop-edit/shop-edit.component';

@NgModule({
  imports: [
    SharedModule,
    ShopManagerRoutingModule
  ],
  providers: [ShopService],
  declarations: [ShopListQueryComponent, OfflineShopListQueryComponent, ShopEditComponent],
  entryComponents: [ShopEditComponent]
})
export class ShopManagerModule {
}
