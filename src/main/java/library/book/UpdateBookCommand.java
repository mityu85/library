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
public class UpdateBookCommand {

    @Schema(example = "Stephen King")
    @NotBlank(message = "author cannot be empty")
    @Size(max = 255)
    private String author;

    @Schema(example = "Christine")
    @NotBlank(message = "title cannot be empty")
    @Size(max = 255)
    private String title;

    @Schema(example = "2021-05-02")
    @NotBlank(message = "date cannot be empty")
    private String rentalDate;
}
