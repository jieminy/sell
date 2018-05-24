import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {NzMessageService, NzModalSubject, UploadFile} from "ng-zorro-antd";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CommonModal} from "../../../shared/common-modal";
import {AdvItem, AdvService} from "../../adv.service";

@Component({
  selector: 'app-adv-edit',
  templateUrl: './adv-edit.component.html',
  styles: []
})
export class AdvEditComponent extends CommonModal implements OnInit, OnDestroy {
  private static messageService: NzMessageService;

  @Input()
  adv: AdvItem;
  validateForm: FormGroup;

  previewImage = '';
  previewVisible = false;

  pics = [];

  constructor(subject: NzModalSubject,
              private fb: FormBuilder,
              private nzMessageService: NzMessageService,
              private advService: AdvService) {
    super(subject);
    AdvEditComponent.messageService = nzMessageService;
  }

  async ngOnInit() {
    this.validateForm = this.fb.group({
      advId: [null],
      categoryId: [null, Validators.required],
      categoryName: [null],
      pic: [null],
      productId: [null, Validators.required],
      type: [null],
    });
    this.validateForm.patchValue(this.adv as any);

    if (this.adv.pic) {
      this.pics = [this.makeFileObj(this.adv.pic)];
    }
  }

  ngOnDestroy() {
    AdvEditComponent.messageService = null;
  }

  emitDataOutside() {
    if (!this.pics || !this.pics.length) {
      AdvEditComponent.messageService.warning('请上传活动图片');
      return false;
    }

    const advItem: AdvItem = Object.assign({}, this.validateForm.value);
    this.pics.forEach(item => {
      if (item.response) {
        advItem.pic = item.response.data[0];
      } else {
        advItem.pic = item.url;
      }
    });
    this.subject.next(advItem);
  }

  // 上传文件之前检测文件是否为图片
  beforeUpload(file: File, fileList: File[]) {
    if (file.type.indexOf('image/') !== 0) {
      AdvEditComponent.messageService.error('仅支持上次图片类型文件！');
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
