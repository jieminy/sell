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

}

export class CategoryItem {
  categoryId?: number;
  parentId?: number;
  pic?: string;
  name?: string;
  createTime?: number;
  childCategories?: CategoryItem[];
  updateTime?: number;
}
