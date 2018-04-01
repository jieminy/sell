import {Component, OnInit} from '@angular/core';
import {UserItem} from "../user-item";
import {UserService} from "../user.service";
import {NzMessageService, NzModalService} from "ng-zorro-antd";
import {UserEditComponent} from "./user-edit/user-edit.component";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styles: []
})
export class UserListComponent implements OnInit {
  dataList: UserItem[] = [];
  _loading = false;

  constructor(private userService: UserService,
              private nzMessage: NzMessageService,
              private nzModal: NzModalService) {
  }

  ngOnInit() {
    this.queryList();
  }

  async queryList() {
    this._loading = true;
    const result = await this.userService.queryAllUser();
    this._loading = false;
    this.dataList = result.data;
  }

  editUser(user: UserItem) {
    this.proxyModal('修改用户', user, async (data) => {
      return this.userService.saveUser(data);
    });
  }

  createUser() {
    this.proxyModal('添加用户', {}, async (data) => {
      return this.userService.saveUser(data);
    });
  }

  delUser(user: UserItem) {
    this.nzModal.confirm({
      title: '删除用户',
      content: '确定删除此用户吗？',
      onOk: async () => {
        const result = await this.userService.delUser(user.sellerId);
        this.nzMessage.info(result.msg);
        this.queryList();
      }
    });
  }

  proxyModal(title, user, callback) {
    const modal = this.nzModal.open({
      title: title,
      content: UserEditComponent,
      componentParams: {user},
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
