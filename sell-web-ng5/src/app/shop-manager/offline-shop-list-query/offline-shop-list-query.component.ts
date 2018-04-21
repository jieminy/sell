import {Component, OnInit} from '@angular/core';
import {ShopItem, ShopService} from "../shop.service";
import {NzMessageService, NzModalService} from "ng-zorro-antd";
import {ShopEditComponent} from "./shop-edit/shop-edit.component";

@Component({
  templateUrl: './offline-shop-list-query.component.html',
  styles: []
})
export class OfflineShopListQueryComponent implements OnInit {
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
    const result = await this.shopService.queryAllProduct('1');
    this._loading = false;
    this.dataList = result.data;
  }

  onShop(shop: ShopItem) {
    this.nzModal.confirm({
      title: '上架商品',
      content: '确定上架商品？',
      onOk: async () => {
        const result = await this.shopService.onShop(shop.productId);
        this.nzMessage.info(result.msg);
        if (result.code === 0) {
          this.queryList();
        }
      }
    });
  }

  createShop() {
    this.proxyModal('添加商品', {}, async (data) => {
      return this.shopService.saveShop(data);
    });
  }

  editShop(shop: ShopItem) {
    this.proxyModal('修改商品', shop, async (data) => {
      return this.shopService.saveShop(data);
    });
  }

  proxyModal(title, shop, callback) {
    const modal = this.nzModal.open({
      title: title,
      width: '930px',
      content: ShopEditComponent,
      componentParams: {shop},
      footer: false,
    });
    modal.subscribe(async (result) => {
      if (typeof result === 'object') {
        result = await callback(result);
        this.nzMessage.info(result.msg);
        if (result.code === 0) {
          this.queryList();
          modal.destroy();
        }
      }
    });
  }

}
