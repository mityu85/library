package library;

import library.book.BookDto;
import library.book.CreateBookCommand;
import library.book.UpdateBookCommand;
import library.reader.CreateReaderCommand;
import library.reader.ReaderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from books")
public class BookControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Test
    public void testListBooks() {
        BookDto bookDto =
            template.postForObject("/api/books",
                    new CreateBookCommand("Stephen King", "Shining", "2021-06-12"), BookDto.class);
        assertEquals("Stephen King", bookDto.getAuthor());

        template.postForObject("/api/books",
                new CreateBookCommand("Stephen King", "Christine", "2021-08-02"), BookDto.class);

        List<BookDto> books = template.exchange("/api/books",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BookDto>>() {})
                .getBody();

        assertThat(books)
                .extracting(BookDto::getTitle)
                .containsExactly("Shining", "Christine");
    }

    @Test
    public void testFindBookById() {
        BookDto bookDto = template.postForObject("/api/books",
                new CreateBookCommand("Stephen King", "Shining", "2021-06-12"), BookDto.class);

        Long id = bookDto.getId();
        BookDto book = template.exchange("/api/books/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<BookDto>() {})
                .getBody();

        assertThat(book)
                .extracting(BookDto::getTitle)
                .isEqualTo("Shining");
    }

    @Test
    public void testUpdateBook() {
        BookDto bookDto = template.postForObject("/api/books",
                new CreateBookCommand("Stephen King", "Shining", "2021-06-12"), BookDto.class);

        Long id = bookDto.getId();
        template.put("/api/books/" + id,
                new UpdateBookCommand("Stephen King", "Christine", "2021-06-12"));

        BookDto anotherBook = template.exchange("/api/books/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<BookDto>() {})
                .getBody();

        assertEquals("Christine", anotherBook.getTitle());
    }

    @Test
    public void testLendBook() {
        ReaderDto readerDto = template.postForObject("/api/readers",
                new CreateReaderCommand("John Doe", "5 Avenue, NY"), ReaderDto.class);
        BookDto bookDto = template.postForObject("/api/books",
                new CreateBookCommand("Stephen King", "Shining", "2021-06-12"), BookDto.class);

        Long readerId = readerDto.getId();
        Long bookId = bookDto.getId();

        template.put("/api/books/" + bookId + "/reader/" + readerId,
                null);

        BookDto anotherBook = template.exchange("/api/books/" + bookId,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<BookDto>() {})
                .getBody();
        assertEquals("Shining", anotherBook.getTitle());
    }
}
