import {Component, OnInit} from '@angular/core';
import {OrderItem, OrderService} from "../order.service";
import {NzMessageService, NzModalService} from "ng-zorro-antd";
import {OrderDetailComponent} from "../order-detail/order-detail.component";

@Component({
  selector: 'app-list-query',
  templateUrl: './order-list-query.component.html',
  styles: []
})
export class OrderListQueryComponent implements OnInit {

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
    const result = await this.orderService.queryOrderAll();
    this._loading = false;
    this.dataList = result.data.content;
  }

  cancelOrder(order: OrderItem) {
    this.nzModal.confirm({
      title: '取消订单',
      content: '确定要取消订单吗？',
      onOk: async () => {
        const result = await this.orderService.cancel(order.orderId);
        this.nzMessage.info(result.msg);
        if (result.code === 0) {
          this.queryList();
        }
      }
    });
  }

  finishOrder(order: OrderItem) {
    this.nzModal.confirm({
      title: '完结订单',
      content: '确定要完结订单吗？',
      onOk: async () => {
        const result = await this.orderService.finish(order.orderId);
        this.nzMessage.info(result.msg);
        if (result.code === 0) {
          this.queryList();
        }
      }
    });
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
