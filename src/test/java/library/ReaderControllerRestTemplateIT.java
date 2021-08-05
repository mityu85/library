package library;

import library.reader.CreateReaderCommand;
import library.reader.ReaderDto;
import library.reader.UpdateReaderCommand;
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
@Sql(statements = "delete from readers")
public class ReaderControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Test
    public void testListReaders() {
        ReaderDto readerDto =
                template.postForObject("/api/readers",
                        new CreateReaderCommand("John Doe", "5 Avenue, NY"), ReaderDto.class);
        assertEquals("John Doe", readerDto.getName());

        template.postForObject("/api/readers",
                new CreateReaderCommand("Jane Doe", "892003 Rodeo Dr, LA"), ReaderDto.class);

        List<ReaderDto> readers = template.exchange("/api/readers",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<ReaderDto>>() {})
                .getBody();

        assertThat(readers)
                .extracting(ReaderDto::getName)
                .containsExactly("John Doe", "Jane Doe");
    }

    @Test
    public void testFindBookById() {
        ReaderDto readerDto =
                template.postForObject("/api/readers",
                    new CreateReaderCommand("Jane Doe", "892003 Rodeo Dr, LA"), ReaderDto.class);

        Long id = readerDto.getId();
        ReaderDto anotherReader = template.exchange("/api/readers/" + id,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<ReaderDto>() {})
                .getBody();

        assertThat(anotherReader)
                .extracting(ReaderDto::getAddress)
                .isEqualTo("892003 Rodeo Dr, LA");
    }

    @Test
    public void testUpdateBook() {
        ReaderDto readerDto =
                template.postForObject("/api/readers",
                        new CreateReaderCommand("John Doe", "5 Avenue, NY"), ReaderDto.class);

        Long id = readerDto.getId();
        template.put("/api/readers/" + id,
                new UpdateReaderCommand("Jane Doe", "892003 Rodeo Dr, LA"));

        ReaderDto anotherReader = template.exchange("/api/readers/" + id,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<ReaderDto>() {})
                .getBody();

        assertEquals("Jane Doe", anotherReader.getName());
    }

//    @Test
//    public void testLendBook() {
//        ReaderDto readerDto = template.postForObject("/api/readers",
//                new CreateReaderCommand("John Doe", "5 Avenue, NY"), ReaderDto.class);
//        BookDto bookDto = template.postForObject("/api/books",
//                new CreateBookCommand("Stephen King", "Shining", "2021-06-12"), BookDto.class);
//
//        Long readerId = readerDto.getId();
//        Long bookId = bookDto.getId();
//
//        template.put("/api/books/" + bookId + "/reader/" + readerId,
//                null);
//
//        BookDto anotherBook = template.exchange("/api/books/" + bookId,
//                        HttpMethod.GET,
//                        null,
//                        new ParameterizedTypeReference<BookDto>() {})
//                .getBody();
//        assertEquals("Shining", anotherBook.getTitle());
//    }
}
