import {NgModule} from '@angular/core';
import {SharedModule} from "../shared/shared.module";
import {OrderManagerRoutingModule} from "./order-manager-routing.module";
import {OrderListQueryComponent} from "./order-list-query/order-list-query.component";
import {HistoryOrderListQueryComponent} from './history-order-list-query/history-order-list-query.component';
import {OrderService} from "./order.service";
import {OrderDetailComponent} from './order-detail/order-detail.component';
import {DistributeTypePipe} from './distribute-type.pipe';

@NgModule({
  imports: [
    SharedModule,
    OrderManagerRoutingModule
  ],
  declarations: [
    OrderListQueryComponent,
    HistoryOrderListQueryComponent,
    OrderDetailComponent,
    DistributeTypePipe
  ],
  providers: [OrderService],
  entryComponents: [OrderDetailComponent]
})
export class OrderManagerModule {
}
