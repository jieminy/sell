import {NgModule} from '@angular/core';
import {SharedModule} from "../shared/shared.module";
import {CategoryManagerRoutingModule} from "./category-manager-routing.module";
import {CategoryListComponent} from './category-list/category-list.component';
import {CategoryService} from "./category.service";
import {CategoryEditComponent} from './category-list/category-edit/category-edit.component';

@NgModule({
  imports: [
    SharedModule,
    CategoryManagerRoutingModule
  ],
  providers: [CategoryService],
  declarations: [CategoryListComponent, CategoryEditComponent],
  entryComponents: [CategoryEditComponent]
})
export class CategoryManagerModule {
}
