package library.reader;

import library.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReaderDto {

    private Long id;
    private String name;
    private String address;
    private List<Book> books;
}
