import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot} from "@angular/router";
import {AuthService} from "./auth.service";

@Injectable()
export class AuthGuardService implements CanActivate, CanActivateChild {

  constructor(private authService: AuthService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.isLogin();
  }

  canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.isLogin();
  }

  // 判断用户是否登录
  isLogin() {
    return this.authService.isLogin().then(isLogin => {
      // 如果没有登录权限，则跳转到登录页
      if (!isLogin) {
        this.router.navigate(['/login']);
      }
      return isLogin;
    });
  }
}
