import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

import {Book} from './book.model';
import {observableToBeFn} from 'rxjs/internal/testing/TestScheduler';

@Injectable()
export class BookService {

  private booksUrl = 'http://localhost:8080/api/books';

  constructor(private httpClient: HttpClient) {
  }

  getAllBooks(): Observable<Book[]> {
    return this.httpClient
      .get<Array<Book>>(this.booksUrl);
  }

  getOneBook(id: number): Observable<Book> {
    return this.getAllBooks()
      .pipe(
        map(books => books.find(book => book.id === id))
      );
  }






}
