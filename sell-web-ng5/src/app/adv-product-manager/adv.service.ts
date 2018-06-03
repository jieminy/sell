import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CategoryItem} from "../category-manager/category.service";
import {ShopItem} from "../shop-manager/shop.service";

@Injectable()
export class AdvService {

  constructor(private http: HttpClient) {
  }

  /**
   * 查询所有广告
   * @returns {Promise<AjaxResult<AdvItem[]>>}
   */
  list(): Promise<AjaxResult<AdvItem[]>> {
    return this.http.get<any>('adv/product/list').toPromise();
  }

  /**
   * 删除广告
   * @param {AdvItem} adv
   * @returns {Promise<AjaxResult<any>>}
   */
  removeAdv(adv: AdvItem): Promise<AjaxResult<any>> {
    return this.http.get<any>('adv/product/del', {
      params: {
        advId: String(adv.advId)
      }
    }).toPromise();
  }

  /**
   * 保存广告
   * @param {AdvItem} adv
   * @returns {Promise<AjaxResult<any>>}
   */
  save(adv: AdvItem): Promise<AjaxResult<any>> {
    return this.http.post<any>('adv/product/save', adv).toPromise();
  }

  // 查询所有大类目
  getCategoryAll(): Promise<AjaxResult<CategoryItem[]>> {
    return this.http.get<any>('seller/category/all').toPromise();
  }

  // 查询所有商品
  queryAllProduct(type = '0'): Promise<AjaxResult<ShopItem[]>> {
    return this.http.get<any>('seller/product/list', {
      params: {type}
    }).toPromise();
  }
}

export class AdvItem {
  advId?: number;
  pic?: string;
  categoryId?: number;
  type?: string;
  productId?: string;
}
