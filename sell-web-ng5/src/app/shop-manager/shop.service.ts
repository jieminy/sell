import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CategoryItem} from "../category-manager/category.service";
import {ActivityItem} from "../activity-manager/activity.service";

@Injectable()
export class ShopService {

  constructor(private http: HttpClient) {
  }

  // 查询所有商品
  queryAllProduct(type = '0'): Promise<AjaxResult<ShopItem[]>> {
    return this.http.get<any>('seller/product/list', {
      params: {type}
    }).toPromise();
  }

  // 查询所有大类目
  getCategoryAll(): Promise<AjaxResult<CategoryItem[]>> {
    return this.http.get<any>('seller/category/all').toPromise();
  }

  // 查询活动列表
  getAtvList(): Promise<AjaxResult<ActivityItem[]>> {
    return this.http.get<any>('activity/list').toPromise();
  }

  // 上架
  delShop(productId): Promise<AjaxResult<any>> {
    return this.http.get<any>('seller/product/del', {
      params: {productId}
    }).toPromise();
  }

  // 上架
  onShop(productId): Promise<AjaxResult<any>> {
    return this.http.get<any>('seller/product/on_sale', {
      params: {productId}
    }).toPromise();
  }

  // 下架
  offShop(productId): Promise<AjaxResult<any>> {
    return this.http.get<any>('seller/product/off_sale', {
      params: {productId}
    }).toPromise();
  }

  // 保存商品
  saveShop(shop: ShopItem): Promise<AjaxResult<any>> {
    return this.http.post<any>('seller/product/save', shop).toPromise();
  }

}

export class ShopItem {
  productDescription: string;
  productIcon: string;
  swipeIcons: string;
  detailIcons: string;
  productId: string;
  productName: string;
  productPrice: number;
  productSales: number;
  productStatus: number;
  parentCategoryId: number;
  parentCategoryName: string;
  atvId: string;
  atvDes: string;
  categoryId: number;
  categoryName: number;
}
