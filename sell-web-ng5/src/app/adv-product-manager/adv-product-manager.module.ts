import {NgModule} from '@angular/core';
import {AdvListQueryComponent} from './adv-list-query/adv-list-query.component';
import {AdvEditComponent} from './adv-list-query/adv-edit/adv-edit.component';
import {SharedModule} from "../shared/shared.module";
import {AdvProductManagerRoutingModule} from "./adv-product-manager-routing.module";
import {AdvService} from "./adv.service";

@NgModule({
  imports: [
    SharedModule,
    AdvProductManagerRoutingModule
  ],
  declarations: [AdvListQueryComponent, AdvEditComponent],
  providers: [AdvService],
  entryComponents: [AdvEditComponent]
})
export class AdvProductManagerModule {
}
