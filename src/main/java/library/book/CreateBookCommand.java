package library.book;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookCommand {

    @Schema(description = "name of the book's author", example = "Stephen King")
    @NotBlank(message = "author cannot be empty")
    @Size(max = 255)
    private String author;

    @Schema(description = "title of the book", example = "Dark Tower")
    @NotBlank(message = "title cannot be empty")
    @Size(max = 255)
    private String title;

    @Schema(description = "date of the rent", example = "2021-05-02")
    @NotBlank(message = "date cannot be empty")
    private String rentalDate;
}
