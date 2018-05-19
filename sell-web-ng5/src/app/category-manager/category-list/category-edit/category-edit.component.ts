import {Component, Input, OnInit} from '@angular/core';
import {NzModalSubject} from "ng-zorro-antd";
import {CategoryItem} from "../../category.service";
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
  smallCategory: CategoryItem;

  @Input()
  parentCategory: CategoryItem;

  validateForm: FormGroup;

  constructor(subject: NzModalSubject,
              private fb: FormBuilder) {
    super(subject);
  }

  ngOnInit() {
    if (this.category) {
      this.validateForm = this.fb.group({
        parentId: [-1],
        categoryId: [null],
        name: [null, [Validators.required]],
      });
      this.validateForm.patchValue(this.category);
    } else {
      this.validateForm = this.fb.group({
        parentId: [null],
        parentCategoryName: [this.parentCategory.name, null],
        categoryId: [null],
        name: [null, [Validators.required]],
        pic: [null],
      });
      this.validateForm.patchValue(this.smallCategory);
    }
  }
}
