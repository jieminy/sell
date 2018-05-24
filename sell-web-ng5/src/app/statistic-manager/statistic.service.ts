import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class StatisticService {

  constructor(private http: HttpClient) {
  }

  count(stDate?, edDate?): Promise<AjaxResult<any>> {
    return this.http.get<any>('statistic/product/num', {
      params: {
        stDate, edDate
      }
    }).toPromise();
  }

}

export class StatisticItem {
  productName?: string;
  num?: number;
}
