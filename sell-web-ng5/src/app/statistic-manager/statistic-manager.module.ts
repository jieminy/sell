import {NgModule} from '@angular/core';
import {StatisticQueryComponent} from './statistic-query/statistic-query.component';
import {StatisticManagerRoutingModule} from "./statistic-manager-routing.module";
import {SharedModule} from "../shared/shared.module";
import {StatisticService} from "./statistic.service";
import {NgZorroAntdModule} from "ng-zorro-antd";

@NgModule({
  imports: [
    NgZorroAntdModule,
    SharedModule,
    StatisticManagerRoutingModule
  ],
  declarations: [StatisticQueryComponent],
  providers: [StatisticService]
})
export class StatisticManagerModule {
}
