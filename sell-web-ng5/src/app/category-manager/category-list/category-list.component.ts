import {Component, OnInit} from '@angular/core';
import {CategoryItem, CategoryService, SmallCategoryItem} from "../category.service";
import {NzMessageService, NzModalService} from "ng-zorro-antd";
import {CategoryEditComponent} from "./category-edit/category-edit.component";

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styles: []
})
export class CategoryListComponent implements OnInit {
  dataList: CategoryItem[] = [];
  _loading = false;

  constructor(private categoryService: CategoryService,
              private nzModal: NzModalService,
              private nzMessage: NzMessageService) {
  }

  ngOnInit() {
    this.queryList();
  }

  async queryList() {
    this._loading = true;
    const result = await this.categoryService.getCategoryAll();
    this._loading = false;
    this.dataList = result.data;
  }

  // 大类操作
  // 新增大类
  createCategory() {
    this.proxyModal('添加大类目', {category: {}}, async (data) => {
      return await this.categoryService.saveCategory(data);
    });
  }

  // 编辑大类
  editCategory(category: CategoryItem) {
    this.proxyModal('修改大类目', {category}, async (data) => {
      return await this.categoryService.saveCategory(data);
    });
  }

  // 删除大类
  delCategory(category: CategoryItem) {
    this.nzModal.confirm({
      title: '删除大类',
      content: '确定删除此大类吗？',
      onOk: async () => {
        const result = await this.categoryService.delCategoryById(category.categoryId);
        this.nzMessage.info(result.msg);
        if (result.code === 0) {
          this.queryList();
        }
      }
    });
  }

  // 小类操作
  // 新增小类
  createSmallCategory(category: CategoryItem) {
    const smallCategory: SmallCategoryItem = {
      categoryId: category.categoryId,
      categoryName: category.categoryName,
      smallCategoryId: null,
      smallCategoryName: null,
      smallPic: null
    };
    this.proxyModal('添加小类目', {smallCategory}, async (data) => {
      return await this.categoryService.saveSmallCategory(data);
    });
  }

  // 修改小类
  editSmallCategory(smallCategory: SmallCategoryItem, category: CategoryItem) {
    smallCategory.categoryName = category.categoryName;
    this.proxyModal('修改小类目', {smallCategory}, async (data) => {
      return await this.categoryService.saveSmallCategory(data);
    });
  }

  // 删除小类
  delSmallCategory(smallCategory: SmallCategoryItem) {
    this.nzModal.confirm({
      title: '删除小类目',
      content: '确定删除此小类目吗？',
      onOk: async () => {
        const result = await this.categoryService.delSmallCategoryById(smallCategory.smallCategoryId);
        this.nzMessage.info(result.msg);
        if (result.code === 0) {
          this.queryList();
        }
      }
    });
  }

  proxyModal(title, params, callback) {
    const modal = this.nzModal.open({
      title: title,
      content: CategoryEditComponent,
      componentParams: params,
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
