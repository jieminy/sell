import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {ShopItem, ShopService} from "../../shop.service";
import {CommonModal} from "../../../shared/common-modal";
import {NzMessageService, NzModalSubject, UploadFile} from "ng-zorro-antd";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CategoryItem} from "../../../category-manager/category.service";
import {ActivityItem} from "../../../activity-manager/activity.service";

@Component({
  selector: 'app-shop-edit',
  templateUrl: './shop-edit.component.html',
  styles: []
})
export class ShopEditComponent extends CommonModal implements OnInit, OnDestroy {
  private static messageService: NzMessageService;

  @Input()
  shop: ShopItem;
  validateForm: FormGroup;

  categoryList: CategoryItem[];
  smallCategoryList: CategoryItem[];
  atvList: ActivityItem[];

  productIcon = [];
  swipeIcons = [];
  detailIcons = [];

  previewImage = '';
  previewVisible = false;

  constructor(subject: NzModalSubject,
              private fb: FormBuilder,
              private nzMessageService: NzMessageService,
              private shopService: ShopService) {
    super(subject);
    ShopEditComponent.messageService = nzMessageService;
  }

  async ngOnInit() {
    this.validateForm = this.fb.group({
      productId: [null],
      atvId: [null],
      atvDes: [null],
      categoryId: [null, Validators.required],
      parentCategoryId: [null, Validators.required],
      productName: [null, Validators.required],
      productIcon: [null],
      productDescription: [null, Validators.required],
      productPrice: [null, Validators.required],

      weight: [null, Validators.required], // 重量
      packing: [null, Validators.required], // 包装
      shelfLife: [null, Validators.required], // 保质期
      storageMode: [null, Validators.required], // 储藏方式
    });
    this.validateForm.patchValue(this.shop as any);

    if (this.shop.productIcon) {
      this.productIcon.push(
        this.makeFileObj(this.shop.productIcon)
      );
    }
    if (this.shop.detailIcons) {
      this.detailIcons = (JSON.parse(this.shop.detailIcons) || []).map(this.makeFileObj);
    }
    if (this.shop.swipeIcons) {
      this.swipeIcons = (JSON.parse(this.shop.swipeIcons) || []).map(this.makeFileObj);
    }

    this.categoryList = (await this.shopService.getCategoryAll()).data;

    this.atvList = (await this.shopService.getAtvList()).data;
    this.setSmallCategory();
  }

  ngOnDestroy() {
    ShopEditComponent.messageService = null;
  }

  setSmallCategory() {
    const {categoryId} = this.validateForm.value;
    const {parentCategoryId} = this.validateForm.value;
    if (parentCategoryId && this.categoryList) {
      const category = this.categoryList.find(e => e.categoryId === parentCategoryId);
      if (category) {
        this.smallCategoryList = category.childCategories;
        const smallCategory = this.smallCategoryList.find(e => e.categoryId === categoryId);
        if (!smallCategory) {
          this.validateForm.patchValue({categoryId: null});
        }
      }
    } else {
      this.smallCategoryList = [];
    }
  }

  emitDataOutside() {
    const shopItem: ShopItem = Object.assign({}, this.validateForm.value);
    if (this.productIcon.length) {// 保存商品主要图片
      shopItem.productIcon = this.getImages(this.productIcon).join('');
    }
    if (this.swipeIcons.length) {// 保存商品轮播图
      shopItem.swipeIcons = JSON.stringify(this.getImages(this.swipeIcons));
    }
    if (this.detailIcons.length) {// 保存商品描述图片
      shopItem.detailIcons = JSON.stringify(this.getImages(this.detailIcons));
    }
    this.subject.next(shopItem);
  }

  // 上传文件之前检测文件是否为图片
  beforeUpload(file: File, fileList: File[]) {
    if (file.type.indexOf('image/') !== 0) {
      ShopEditComponent.messageService.error('仅支持上次图片类型文件！');
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
