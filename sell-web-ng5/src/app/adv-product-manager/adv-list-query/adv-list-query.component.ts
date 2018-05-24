import {Component, OnInit} from '@angular/core';
import {AdvItem, AdvService} from "../adv.service";
import {NzMessageService, NzModalService} from "ng-zorro-antd";
import {AdvEditComponent} from "./adv-edit/adv-edit.component";

@Component({
  selector: 'app-adv-list-query',
  templateUrl: './adv-list-query.component.html',
  styles: []
})
export class AdvListQueryComponent implements OnInit {
  dataList: AdvItem[] = [];
  _loading = false;

  constructor(private advService: AdvService,
              private nzModal: NzModalService,
              private nzMessage: NzMessageService) {
  }

  ngOnInit() {
    this.queryList();
  }

  async queryList() {
    this._loading = true;
    this.dataList = (await this.advService.list()).data;
    this._loading = false;
  }

  // 创建广告
  createAdv() {
    this.proxyModal('添加广告', {}, async (data) => {
      return this.advService.save(data);
    });
  }

  // 修改广告
  editAdv(adv: AdvItem) {
    this.proxyModal('修改广告', adv, async (data) => {
      return this.advService.save(data);
    });
  }

  // 删除广告
  removeAdv(adv: AdvItem) {
    this.nzModal.confirm({
      content: '确定删除此广告？',
      onOk: async () => {
        await this.advService.removeAdv(adv);
        this.queryList();
      }
    });
  }

  proxyModal(title, adv: AdvItem, callback) {
    const modal = this.nzModal.open({
      title: title,
      width: '930px',
      content: AdvEditComponent,
      componentParams: {adv},
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
