import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {NzMessageService, NzModalSubject, UploadFile} from "ng-zorro-antd";
import {CommonModal} from "../../../shared/common-modal";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivityItem} from "../../activity.service";

@Component({
  selector: 'app-activity-edit',
  templateUrl: './activity-edit.component.html',
  styles: []
})
export class ActivityEditComponent extends CommonModal implements OnInit, OnDestroy {
  private static messageService: NzMessageService;

  @Input()
  atv: ActivityItem;
  validateForm: FormGroup;

  previewImage = '';
  previewVisible = false;

  icons = [];

  constructor(subject: NzModalSubject,
              private fb: FormBuilder,
              private nzMessageService: NzMessageService) {
    super(subject);
    ActivityEditComponent.messageService = nzMessageService;
  }

  async ngOnInit() {
    this.validateForm = this.fb.group({
      atvId: [null],
      type: [null],
      discount: [null, Validators.required],
      des: [null, Validators.required],
      icon: [null],
    });
    this.validateForm.patchValue(this.atv as any);

    if (this.atv.icon) {
      this.icons = [this.makeFileObj(this.atv.icon)];
    }
  }

  ngOnDestroy() {
    ActivityEditComponent.messageService = null;
  }

  emitDataOutside() {
    if (!this.icons || !this.icons.length) {
      ActivityEditComponent.messageService.warning('请上传小图标');
      return false;
    }

    const activityItem: ActivityItem = Object.assign({}, this.validateForm.value);
    this.icons.forEach(item => {
      if (item.response) {
        activityItem.icon = item.response.data[0];
      } else {
        activityItem.icon = item.url;
      }
    });
    this.subject.next(activityItem);
  }

  // 上传文件之前检测文件是否为图片
  beforeUpload(file: File, fileList: File[]) {
    if (file.type.indexOf('image/') !== 0) {
      ActivityEditComponent.messageService.error('仅支持上次图片类型文件！');
      fileList.splice(fileList.indexOf(file), 1);
      return false;
    }
  }

  getImages(list) {
    return list.map(function (item) {
      if (item.url) {
        return item.url;
      }
      if (item.response) {
        const result: AjaxResult<string[]> = item.response;
        return result.data[0];
      }
    });
  }

  makeFileObj(imgUrl: string) {
    return {
      uid: -1,
      name: imgUrl.slice(imgUrl.lastIndexOf('/') + 1),
      status: 'done',
      url: imgUrl
    };
  }

  handlePreview(file: UploadFile) {
    this.previewImage = file.url || file.thumbUrl;
    this.previewVisible = true;
  }
}
