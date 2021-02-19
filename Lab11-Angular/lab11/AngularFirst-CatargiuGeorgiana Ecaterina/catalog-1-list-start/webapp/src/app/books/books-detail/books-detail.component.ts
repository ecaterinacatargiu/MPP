import {Component, Input, OnInit} from '@angular/core';
import {BookService} from '../shared/book.service';
import {ActivatedRoute, Params} from '@angular/router';
import {Location} from '@angular/common';
import {Book} from '../shared/book.model';

import {switchMap} from 'rxjs/operators';


@Component({
  // tslint:disable-next-line:component-selector
  selector: 'ubb-book-detail',
  templateUrl: './books-detail.component.html',
  styleUrls: ['./books-detail.component.css'],
})

export class BookDetailComponent implements OnInit {

  @Input() book: Book;

  constructor(private bookService: BookService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.bookService.getOneBook(+params['id'])))
      .subscribe(book => this.book = book);
  }

  goBack(): void {
    this.location.back();
  }

}

