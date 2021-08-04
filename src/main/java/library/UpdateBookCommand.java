package library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookCommand {

    @NotBlank(message = "author cannot be empty")
    @Size(max = 255)
    private String author;

    @NotBlank(message = "title cannot be empty")
    @Size(max = 255)
    private String title;

    @NotBlank(message = "date cannot be empty")
    private String rentalDate;
}
