package ro.ubb.catalog.core.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Book;
import ro.ubb.catalog.core.repository.BookRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService implements BookServiceInterface
{
    public static final Logger log = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    public BookService()
    {

    }

    @Override
    @Transactional
    public Book addBook(Book book)
    {
        log.trace("Add book - method entered");
         return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book updateBook(Book book)
    {
        log.trace("Update book - method entered: book={}", book);
        Book b = bookRepository.findById(book.getId()).orElse(book);
        b.setBID(book.getBID());
        b.setAuthor(book.getAuthor());
        b.setYear(book.getYear());
        return b;
    }

    @Override
    @Transactional
    public void deleteBook(Long bookID){
        log.trace("Delete book = method entered: bookId={}", bookID);
        if(checkBook(bookID))
        {
            bookRepository.deleteById(bookID);

        }
        log.trace("Delete Book - method finished");
    }

    @Override
    @Transactional
    public Optional<Book> getOneBook(Long bookID)
    {
        log.trace("GetOneBook - method entered find book with bookID={}", bookID);
        log.trace("GetOneBook - method finished");
        return bookRepository.findById(bookID);
    }

    @Override
    @Transactional
    public boolean checkBook(Long bid)
    {
        log.trace("CheckBook - method entered check if a book exists with bookID={}", bid);
        //bookRepository.findOne(bid).isPresent();
        //log.trace("CheckBook - method finished");
        return true;
    }

    @Override
    @Transactional
    public List<Book> getAllBooks()
    {
        log.trace("GetAllBooks - method entered");
        return bookRepository.findAll();
    }

}
