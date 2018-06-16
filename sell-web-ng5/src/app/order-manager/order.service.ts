import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class OrderService {

  constructor(private http: HttpClient) {
  }

  queryOrderAll(): Promise<AjaxResult<PagingResult<OrderItem[]>>> {
    return this.http.get<any>('seller/order/list', {params: {size: '99999'}}).toPromise();
  }

  queryHistoryOrderAll(): Promise<AjaxResult<PagingResult<OrderItem[]>>> {
    return this.http.get<any>('seller/order/list/history', {params: {size: '99999'}}).toPromise();
  }

  // 取消订单
  cancel(orderId): Promise<AjaxResult<OrderItem[]>> {
    return this.http.get<any>('seller/order/cancel', {
      params: {orderId}
    }).toPromise();
  }

  // 查询订单详情
  detail(orderId): Promise<AjaxResult<OrderDetailItem>> {
    return this.http.get<any>('seller/order/detail', {
      params: {orderId}
    }).toPromise();
  }

  // 完结订单
  finish(orderId): Promise<AjaxResult<any>> {
    return this.http.get<any>('seller/order/finish', {
      params: {orderId}
    }).toPromise();
  }
}

export class OrderItem {
  buyerAddress: string;
  buyerName: string;
  buyerOpenid: string;
  buyerPhone: string;
  orderAmount: number;
  orderId: string;
  orderStatus: number;
  payStatus: number;
}

export class OrderDetailItem {
  buyerAddress: string;
  buyerName: string;
  buyerOpenid: string;
  buyerPhone: string;
  orderAmount: number;
  orderDetailList: OrderDetailInfoItem[];
  orderId: string;
  orderStatus: number;
  payStatus: number;
}

export class OrderDetailInfoItem {
  count: number;
  detailId: string;
  orderId: string;
  productIcon: string;
  productId: string;
  productName: string;
  productPrice: number;
}
