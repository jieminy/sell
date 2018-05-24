import {NgModule} from "@angular/core";
import {Route, RouterModule} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {MainLayoutComponent} from "./main-layout/main-layout.component";
import {IndexComponent} from "./index/index.component";
import {AuthGuardService} from "./shared/auth-guard.service";
import {SharedModule} from "./shared/shared.module";

const routes: Route[] = [
  {path: '', pathMatch: 'full', redirectTo: 'main/index'},
  {
    path: 'main', component: MainLayoutComponent,
    canActivate: [AuthGuardService],
    canActivateChild: [AuthGuardService],
    children: [
      {path: 'index', component: IndexComponent},
      {path: 'shop-manager', loadChildren: './shop-manager/shop-manager.module#ShopManagerModule'},
      {path: 'order-manager', loadChildren: './order-manager/order-manager.module#OrderManagerModule'},
      {path: 'category-manager', loadChildren: './category-manager/category-manager.module#CategoryManagerModule'},
      {path: 'user-manager', loadChildren: './user-manager/user-manager.module#UserManagerModule'},
      {
        path: 'adv-product-manager',
        loadChildren: './adv-product-manager/adv-product-manager.module#AdvProductManagerModule'
      },
      {path: 'activity-manager', loadChildren: './activity-manager/activity-manager.module#ActivityManagerModule'},
      {path: 'statistic-manager', loadChildren: './statistic-manager/statistic-manager.module#StatisticManagerModule'},
    ]
  },
  {
    path: 'login', component: LoginComponent
  }
];

@NgModule({
  imports: [SharedModule, RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
