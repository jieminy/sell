import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class AuthService {

  currentUser: User;

  constructor(private http: HttpClient) {
  }

  login(data: User): Promise<AjaxResult<any>> {
    return this.http.post<any>('seller/user/login', data).toPromise();
  }

  logout(): Promise<AjaxResult<any>> {
    return this.http.get<any>('seller/user/logout').toPromise();
  }

  isLogin(): Promise<boolean> {
    if (this.currentUser) {
      return Promise.resolve(true);
    }

    return this.http.get<AjaxResult<User>>('seller/user/getCurrentUser').toPromise().then((result) => {
      if (result && result.data) {
        this.currentUser = result.data;
      }
      return !!result.data;
    });
  }

}


export interface User {
  admin?: boolean;
  username?: string;
  password?: string;
}
