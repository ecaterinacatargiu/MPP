import {Component, OnInit} from '@angular/core';
import {BookService} from '../shared/book.service';
import {Book} from '../shared/book.model';

import {Router} from '@angular/router';


@Component({

  moduleId: module.id,
  selector: 'ubb-book-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.css'],
})

export class BooksListComponent implements OnInit {

  errorMessage: string;
  books: Array<Book>;
  selectedBook: Book;

  constructor(private bookService: BookService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getAllBooks();
  }

  getAllBooks() {
      console.log('In getAllBooks book-list comp'),
      this.bookService.getAllBooks()
      .subscribe(
        books => this.books = books,
        error => this.errorMessage = <any>error
      );
  }

  onSelect(book: Book): void {
    this.selectedBook = book;
  }

  goToDetail(): void {
    this.router.navigate(['/book/detail', this.selectedBook.id]);
  }

}
