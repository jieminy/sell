import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {SharedModule} from "./shared/shared.module";
import {NgZorroAntdModule} from "ng-zorro-antd";
import {AppRoutingModule} from "./app-routing.module";
import {LoginComponent} from './login/login.component';
import {MainLayoutComponent} from "./main-layout/main-layout.component";
import {IndexComponent} from './index/index.component';
import {BrowserModule} from "@angular/platform-browser";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import "rxjs/add/operator/toPromise";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MainLayoutComponent,
    IndexComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    NgZorroAntdModule.forRoot(),
    SharedModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
