import {Injectable} from '@angular/core';
import {HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {environment} from "../../environments/environment";

@Injectable()
export class HttpProxyInterceptorService implements HttpInterceptor {

  /**
   * 开发模式api代理转发设置
   * 通过配置 proxy.config.json 来将请求转发到代理服务器，到生产环境时，此设置自动取消
   * @param {HttpRequest<any>} req
   * @param {HttpHandler} next
   * @returns {Observable<HttpEvent<any>>}
   */
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let secureReq = req;
    if (!environment.production) {// 判断是否为非生产环境
      secureReq = req.clone({
        url: '/api/' + req.url
      });
    }

    secureReq = secureReq.clone({
      setParams: {_time: Date.now().toString()}
    });

    return next.handle(secureReq);
  }

}

export const httpInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: HttpProxyInterceptorService, multi: true},
];
