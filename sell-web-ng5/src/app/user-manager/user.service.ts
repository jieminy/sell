import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserItem} from "./user-item";

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  // 查询全部用户
  queryAllUser(): Promise<AjaxResult<UserItem[]>> {
    return this.http.get<any>('seller/user/list').toPromise();
  }

  // 删除用户
  delUser(userId): Promise<AjaxResult<any>> {
    return this.http.get<any>('seller/user/list', {
      params: {userId}
    }).toPromise();
  }

  // 保存用户
  saveUser(user: UserItem): Promise<AjaxResult<any>> {
    return this.http.post<any>('seller/user/save', user).toPromise();
  }
}
