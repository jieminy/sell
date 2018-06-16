import {Component, OnInit} from '@angular/core';
import {OrderItem, OrderService} from "../order.service";
import {NzMessageService, NzModalService} from "ng-zorro-antd";
import {OrderDetailComponent} from "../order-detail/order-detail.component";

@Component({
  templateUrl: './history-order-list-query.component.html',
  styles: []
})
export class HistoryOrderListQueryComponent implements OnInit {

  dataList: OrderItem[] = [];
  _loading = false;

  constructor(private orderService: OrderService,
              private nzMessage: NzMessageService,
              private nzModal: NzModalService) {
  }

  ngOnInit() {
    this.queryList();
  }

  async queryList() {
    this._loading = true;
    const result = await this.orderService.queryHistoryOrderAll();
    this._loading = false;
    this.dataList = result.data.content;
  }

  showDetail(order: OrderItem) {
    this.nzModal.open({
      title: '查看订单',
      width: 700,
      content: OrderDetailComponent,
      componentParams: {order}
    });
  }
}
