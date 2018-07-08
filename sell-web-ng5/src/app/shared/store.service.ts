import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class StoreService {

  constructor(private http: HttpClient) {
  }

  /**
   * 切换开店或者闭店
   * @returns {Promise<AjaxResult<any>>}
   */
  toggleStoreState(): Promise<AjaxResult<any>> {
    return this.http.get<AjaxResult<any>>("mini/close/or/open").toPromise();
  }

  /**
   * 判断是否开店
   * @returns {Promise<boolean>}
   */
  isOpen(): Promise<boolean> {
    return this.http.get<AjaxResult<MiniProgramInfo>>("mini/close/or/open").toPromise()
      .then<boolean>(result => {
        if (result.code === 0) {
          return result.data.status === 1;
        } else {
          throw result.msg;
        }
      });
  }

}


export class MiniProgramInfo {
  programId: string;
  status: number;
}
