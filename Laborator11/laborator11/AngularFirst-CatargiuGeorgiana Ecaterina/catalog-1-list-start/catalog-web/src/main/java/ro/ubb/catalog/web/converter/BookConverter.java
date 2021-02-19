package ro.ubb.catalog.web.converter;


import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Book;
import ro.ubb.catalog.web.dto.BookDto;

@Component
public class BookConverter extends BaseConverter<Book, BookDto>{
    @Override
    public Book convertDtoToModel(BookDto dto) {
        Book book = Book.builder()
                .BID(dto.getBID())
                .author(dto.getAuthor())
                .year(dto.getYear())
                .build();
        book.setId(dto.getId());
        return book;
    }

    @Override
    public BookDto convertModelToDto(Book book) {
        BookDto dto = BookDto.builder()
                .BID(book.getBID())
                .author(book.getAuthor())
                .year(book.getYear())
                .build();
        dto.setId(book.getId());
        return dto;
    }
}
