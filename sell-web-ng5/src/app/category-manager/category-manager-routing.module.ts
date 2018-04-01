import {NgModule} from "@angular/core";
import {Route, RouterModule} from "@angular/router";
import {CategoryListComponent} from "./category-list/category-list.component";

const routes: Route[] = [
  {path: '', redirectTo: 'list'},
  {path: 'list', component: CategoryListComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CategoryManagerRoutingModule {
}
