import {Injectable} from '@angular/core';
import {
  HTTP_INTERCEPTORS,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse,
} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable} from "rxjs/Observable";
import {NzModalService} from "ng-zorro-antd";
import "rxjs/add/operator/do";

@Injectable()
export class UserAuthInterceptorService implements HttpInterceptor {
  constructor(private modalService: NzModalService,
              private router: Router) {
  }

  /**
   * 通用ajax拦截，用户未登陆时，跳转到登录页
   * @param {HttpRequest<any>} req
   * @param {HttpHandler} next
   * @returns {Observable<HttpEvent<any>>}
   */
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).do((event: HttpEvent<any>) => {
      if (event instanceof HttpResponse) {
        const data: AjaxResult<any> = event.body;
        if (data.code === 2) {
          // 未登录
          this.modalService.error({
            content: data.msg
          }).on('onDestroy', () => {
            this.router.navigate(['/login']);
          });
        }
      }
    });
  }
}

export const UserAuthInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: UserAuthInterceptorService, multi: true},
];
