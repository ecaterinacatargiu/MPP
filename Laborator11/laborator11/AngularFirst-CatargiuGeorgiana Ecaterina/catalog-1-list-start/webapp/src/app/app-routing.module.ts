import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BooksListComponent} from './books/books-list/books-list.component';
import {BooksComponent} from './books/books.component';



const routes: Routes = [

  {path: 'books', component: BooksComponent},
  {path: 'books/detail/:id', component: BooksListComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
