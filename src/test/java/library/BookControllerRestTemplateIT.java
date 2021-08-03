package library;

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
            template.postForObject("/api/library",
                    new CreateBookCommand("Stephen King", "Shining", "2021-06-12"), BookDto.class);
        assertEquals("Stephen King", bookDto.getAuthor());

        template.postForObject("/api/library",
                new CreateBookCommand("Stephen King", "Christine", "2021-08-02"), BookDto.class);

        List<BookDto> books = template.exchange("/api/library",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BookDto>>() {})
                .getBody();

        assertThat(books)
                .extracting(BookDto::getTitle)
                .containsExactly("Shining", "Christine");
    }
}
