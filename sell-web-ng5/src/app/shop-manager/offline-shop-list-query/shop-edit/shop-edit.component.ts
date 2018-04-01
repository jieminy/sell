import {Component, Input, OnInit} from '@angular/core';
import {ShopItem, ShopService} from "../../shop.service";
import {CommonModal} from "../../../shared/common-modal";
import {NzModalSubject} from "ng-zorro-antd";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CategoryItem, SmallCategoryItem} from "../../../category-manager/category.service";

@Component({
  selector: 'app-shop-edit',
  templateUrl: './shop-edit.component.html',
  styles: []
})
export class ShopEditComponent extends CommonModal implements OnInit {
  @Input()
  shop: ShopItem;
  validateForm: FormGroup;

  categoryList: CategoryItem[];
  smallCategoryList: SmallCategoryItem[];

  constructor(subject: NzModalSubject,
              private fb: FormBuilder,
              private shopService: ShopService) {
    super(subject);
  }

  async ngOnInit() {
    this.validateForm = this.fb.group({
      productId: [null],
      categoryId: [null, Validators.required],
      smallCategoryId: [null, Validators.required],
      productName: [null, Validators.required],
      productIcon: [null, Validators.required],
      productDescription: [null, Validators.required],
      productPrice: [null, Validators.required],
    });
    this.validateForm.patchValue(this.shop);

    const result = await this.shopService.getCategoryAll();
    this.categoryList = result.data;
    this.setSmallCategory();
  }

  setSmallCategory() {
    const {categoryId} = this.validateForm.value;
    const {smallCategoryId} = this.validateForm.value;
    if (categoryId && this.categoryList) {
      const category = this.categoryList.find(e => e.categoryId === categoryId);
      if (category) {
        this.smallCategoryList = category.productSmallCategories;
        const smallCategory = this.smallCategoryList.find(e => e.smallCategoryId === smallCategoryId);
        if (!smallCategory) {
          this.validateForm.patchValue({smallCategoryId: null});
        }
      }
    } else {
      this.smallCategoryList = [];
    }
  }

}
