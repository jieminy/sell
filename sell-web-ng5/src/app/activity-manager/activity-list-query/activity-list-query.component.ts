import {Component, OnInit} from '@angular/core';
import {NzMessageService, NzModalService} from "ng-zorro-antd";
import {ActivityItem, ActivityService} from "../activity.service";
import {ActivityEditComponent} from "./activity-edit/activity-edit.component";

@Component({
  selector: 'app-activity-list-query',
  templateUrl: './activity-list-query.component.html',
  styles: []
})
export class ActivityListQueryComponent implements OnInit {

  dataList: ActivityItem[] = [];
  _loading = false;

  constructor(private activityService: ActivityService,
              private nzModal: NzModalService,
              private nzMessage: NzMessageService) {
  }

  ngOnInit() {
    this.queryList();
  }

  async queryList() {
    this._loading = true;
    this.dataList = (await this.activityService.list()).data;
    this._loading = false;
  }

  // 创建活动
  createAdv() {
    this.proxyModal('添加活动', {}, async (data) => {
      return this.activityService.save(data);
    });
  }

  // 修改活动
  editAtv(activityItem: ActivityItem) {
    this.proxyModal('修改活动', activityItem, async (data) => {
      return this.activityService.save(data);
    });
  }

  // 删除活动
  removeAtv(activityItem: ActivityItem) {
    this.nzModal.confirm({
      content: '确定删除此活动？',
      onOk: async () => {
        await this.activityService.removeAdv(activityItem);
        this.queryList();
      }
    });
  }

  proxyModal(title, atv: ActivityItem, callback) {
    const modal = this.nzModal.open({
      title: title,
      width: '930px',
      content: ActivityEditComponent,
      componentParams: {atv},
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
