import {Component, Input, OnInit} from '@angular/core';
import {NzModalSubject} from "ng-zorro-antd";
import {CategoryItem, SmallCategoryItem} from "../../category.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CommonModal} from "../../../shared/common-modal";

@Component({
  selector: 'app-category-edit',
  templateUrl: './category-edit.component.html',
  styles: []
})
export class CategoryEditComponent extends CommonModal implements OnInit {
  @Input()
  category: CategoryItem;
  @Input()
  smallCategory: SmallCategoryItem;

  validateForm: FormGroup;

  constructor(subject: NzModalSubject,
              private fb: FormBuilder) {
    super(subject);
  }

  ngOnInit() {
    if (this.category) {
      this.validateForm = this.fb.group({
        categoryId: [null],
        categoryName: [null, [Validators.required]],
      });
      this.validateForm.patchValue(this.category);
    } else {
      this.validateForm = this.fb.group({
        categoryName: [null],
        categoryId: [null],
        smallCategoryId: [null],
        smallCategoryName: [null, [Validators.required]],
        smallPic: [null, [Validators.required]],
      });
      this.validateForm.patchValue(this.smallCategory);
    }
  }
}
