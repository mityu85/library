package library.reader;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "reader's name", example = "John Doe")
    @NotBlank(message = "reader's name cannot be blank")
    @Size(max = 255)
    private String name;

    @Schema(description = "reader's address", example = "25 Ave, NY")
    @NotBlank(message = "reader's address cannot be blank")
    @Size(max = 255)
    private String address;
}
