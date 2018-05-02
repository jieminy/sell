import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {AppComponent} from './app.component';
import {SharedModule} from "./shared/shared.module";
import {BrowserModule} from "@angular/platform-browser";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {AppRoutingModule} from "./app-routing.module";
import {NgZorroAntdModule} from "ng-zorro-antd";
import {LoginComponent} from "./login/login.component";
import {MainLayoutComponent} from "./main-layout/main-layout.component";
import {IndexComponent} from "./index/index.component";

describe('AppComponent', () => {
  let fixture: ComponentFixture<AppComponent>;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        NgZorroAntdModule.forRoot(),
        SharedModule
      ],
      declarations: [
        AppComponent,
        LoginComponent,
        MainLayoutComponent,
        IndexComponent
      ],
    }).compileComponents();
    fixture = TestBed.createComponent(AppComponent);
  }));
  it(' AppComponent 组件应该可以正确的创建', async(() => {
    const appComponent = fixture.debugElement.componentInstance;
    expect(appComponent).toBeTruthy();
  }));
  it(' AppComponent 组件应该可以渲染出 router-outlet 组件', async(() => {
    fixture.detectChanges();
    const appElement: Element = fixture.debugElement.nativeElement;
    const outerElement: Element = appElement.querySelector('router-outlet');
    expect(outerElement.localName).toBe('router-outlet');
  }));
});
