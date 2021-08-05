package library.book;

import library.reader.Reader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;
    private String author;
    private String title;
    private String rentalDate;
    private Reader reader;
}
