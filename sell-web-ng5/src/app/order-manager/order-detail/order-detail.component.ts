import {Component, Input, OnInit} from '@angular/core';
import {OrderDetailInfoItem, OrderDetailItem, OrderItem, OrderService} from "../order.service";

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styles: []
})
export class OrderDetailComponent implements OnInit {

  @Input()
  order: OrderItem;

  orderDetailItem: OrderDetailItem;
  orderDetailList: OrderDetailInfoItem[] = [];

  constructor(private orderService: OrderService) {
  }

  async ngOnInit() {
    const result = await this.orderService.detail(this.order.orderId);

    this.orderDetailItem = result.data;
    this.orderDetailList = result.data.orderDetailList;
  }

}
