package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Book;
import ro.ubb.catalog.core.service.BookServiceInterface;
import ro.ubb.catalog.web.converter.BookConverter;
import ro.ubb.catalog.web.dto.BookDto;
import ro.ubb.catalog.web.dto.BooksDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class BookController {

    public static final Logger log= LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookServiceInterface bookService;

    @Autowired
    private BookConverter bookConverter;

    @RequestMapping(value="/books", method = RequestMethod.GET)
    List<BookDto> getAllBooks()
    {
        log.trace("getBooks");
        List<Book> books = bookService.getAllBooks();
        log.trace("getBooks: books={}", books);

        return new ArrayList<>(bookConverter.convertModelsToDtos(books));
    }

    @RequestMapping(value="/books", method = RequestMethod.POST)
    BookDto addBook(@RequestBody BookDto bookDto)
    {
        return bookConverter.convertModelToDto(bookService.addBook(
                bookConverter.convertDtoToModel(bookDto)
        ));
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    BookDto updateBook(@PathVariable Long id, @RequestBody BookDto studentDto) {
        Book b = bookConverter.convertDtoToModel(studentDto);
        b.setId(id);
        return bookConverter.convertModelToDto(bookService.updateBook(b));
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
