import {NgModule} from '@angular/core';
import {ActivityListQueryComponent} from './activity-list-query/activity-list-query.component';
import {ActivityEditComponent} from './activity-list-query/activity-edit/activity-edit.component';
import {SharedModule} from "../shared/shared.module";
import {ActivityManagerRoutingModule} from "./activity-manager-routing.module";
import {ActivityService} from "./activity.service";

@NgModule({
  imports: [
    SharedModule,
    ActivityManagerRoutingModule
  ],
  declarations: [ActivityListQueryComponent, ActivityEditComponent],
  providers: [ActivityService],
  entryComponents: [ActivityEditComponent]
})
export class ActivityManagerModule {
}
