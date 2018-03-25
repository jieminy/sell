import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {SharedModule} from "./shared/shared.module";
import {NgZorroAntdModule} from "ng-zorro-antd";


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    NgZorroAntdModule.forRoot(),
    SharedModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
