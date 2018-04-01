import {Component, OnInit} from '@angular/core';
import {ShopItem, ShopService} from "../shop.service";
import {NzMessageService, NzModalService} from "ng-zorro-antd";

@Component({
  templateUrl: './shop-list-query.component.html',
  styles: []
})
export class ShopListQueryComponent implements OnInit {
  dataList: ShopItem[] = [];
  _loading = false;

  constructor(private shopService: ShopService,
              private nzMessage: NzMessageService,
              private nzModal: NzModalService) {
  }

  ngOnInit() {
    this.queryList();
  }

  async queryList() {
    this._loading = true;
    const result = await this.shopService.queryAllProduct('0');
    this._loading = false;
    this.dataList = result.data;
  }

  offlineShop(shop: ShopItem) {
    this.nzModal.confirm({
      title: '下架商品',
      content: '确定下架商品？',
      onOk: async () => {
        const result = await this.shopService.offShop(shop.productId);
        this.nzMessage.info(result.msg);
        if (result.code === 0) {
          this.queryList();
        }
      }
    });
  }
}
