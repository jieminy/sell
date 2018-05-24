import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class ActivityService {

  constructor(private http: HttpClient) {
  }

  /**
   * 查询所有广告
   * @returns {Promise<AjaxResult<ActivityItem[]>>}
   */
  list(): Promise<AjaxResult<ActivityItem[]>> {
    return this.http.get<any>('activity/list').toPromise();
  }

  /**
   * 删除广告
   * @param {ActivityItem} adv
   * @returns {Promise<AjaxResult<any>>}
   */
  removeAdv(adv: ActivityItem): Promise<AjaxResult<any>> {
    return this.http.get<any>('activity/del', {
      params: {
        atvId: adv.atvId
      }
    }).toPromise();
  }

  /**
   * 保存广告
   * @param {ActivityItem} adv
   * @returns {Promise<AjaxResult<any>>}
   */
  save(adv: ActivityItem): Promise<AjaxResult<any>> {
    return this.http.post<any>('activity/save', adv).toPromise();
  }
}

export class ActivityItem {
  atvId?: string;
  type?: number;
  discount?: number;
  des?: string;
  icon?: string;
}
