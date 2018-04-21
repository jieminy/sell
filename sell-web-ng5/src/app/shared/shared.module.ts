import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {httpInterceptorProviders} from "./http-proxy.interceptor";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgZorroAntdModule} from "ng-zorro-antd";
import {AuthService} from "./auth.service";
import {AuthGuardService} from "./auth-guard.service";
import {StatusPipe} from './status.pipe';
import {UserAuthInterceptorProviders} from "./user-auth.interceptor";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgZorroAntdModule,
  ],
  declarations: [StatusPipe],
  providers: [
    UserAuthInterceptorProviders,
    httpInterceptorProviders,
    AuthService,
    AuthGuardService
  ],
  exports: [
    CommonModule, FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgZorroAntdModule,
  ]
})
export class SharedModule {
}
