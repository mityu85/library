package library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReaderCommand {

    @NotBlank(message = "reader's name cannot be blank")
    @Size(max = 255)
    private String name;

    @NotBlank(message = "reader's address cannot be blank")
    @Size(max = 255)
    private String address;

    @NotBlank(message = "list of book cannot be empty")
    private List<Book> books;
}
