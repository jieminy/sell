import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class CategoryService {

  constructor(private http: HttpClient) {
  }

  // 查询所有大类目
  getCategoryAll(): Promise<AjaxResult<CategoryItem[]>> {
    return this.http.get<any>('seller/category/all').toPromise();
  }

  // 删除类目
  delCategoryById(categoryId): Promise<AjaxResult<Object>> {
    return this.http.get<any>('seller/category/del', {
      params: {categoryId}
    }).toPromise();
  }

  // 新增或者删除类目
  saveCategory(category: CategoryItem): Promise<AjaxResult<Object>> {
    return this.http.post<any>('seller/category/save', category).toPromise();
  }

  // 删除小类目
  delSmallCategoryById(smallCategoryId): Promise<AjaxResult<Object>> {
    return this.http.get<any>('seller/smallCategory/del', {
      params: {smallCategoryId}
    }).toPromise();
  }

  // 新增或者删除小类目
  saveSmallCategory(smallCategory: SmallCategoryItem): Promise<AjaxResult<Object>> {
    return this.http.post<any>('seller/smallCategory/save', smallCategory).toPromise();
  }

}

export class CategoryItem {
  categoryId?: number;
  categoryName?: string;
  createTime?: number;
  productSmallCategories?: SmallCategoryItem[];
  updateTime?: number;
}

export class SmallCategoryItem {
  categoryId?: number;
  categoryName?: string;
  smallCategoryId?: number;
  smallCategoryName?: number;
  smallPic?: string;
}
