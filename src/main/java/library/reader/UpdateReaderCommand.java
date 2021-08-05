package library.reader;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReaderCommand {

    @Schema(example = "Jane Doe")
    @NotBlank(message = "reader's name cannot be blank")
    @Size(max = 255)
    private String name;

    @Schema(example = "82009 Rodeo Dr, LA")
    @NotBlank(message = "reader's address cannot be blank")
    @Size(max = 255)
    private String address;
}
