import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {BookService} from './books/shared/book.service';
import {BooksListComponent} from './books/books-list/books-list.component';
import {BookDetailComponent} from './books/books-detail/books-detail.component';
import {BooksComponent} from './books/books.component';


@NgModule({
  declarations: [
    AppComponent,
    BooksComponent,
    BooksListComponent,
    BookDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [BookService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
