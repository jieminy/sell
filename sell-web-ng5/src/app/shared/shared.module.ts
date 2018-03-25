import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BrowserModule} from "@angular/platform-browser";
import {httpInterceptorProviders} from "./http-proxy.interceptor";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {NgZorroAntdModule} from "ng-zorro-antd";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    BrowserModule,
    NgZorroAntdModule,
    BrowserAnimationsModule
  ],
  declarations: [],
  providers: [
    httpInterceptorProviders
  ],
  exports: [
    CommonModule, FormsModule,
    HttpClientModule, BrowserModule,
    NgZorroAntdModule,
    BrowserAnimationsModule,
  ]
})
export class SharedModule {
}
