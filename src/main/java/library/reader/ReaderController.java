package library.reader;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/readers")
@Tag(name = "Reader controller layer", description = "This layer responsible for control the operations on Reader entity")
public class ReaderController {

    private ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping
    @Operation(summary = "list all reader in library")
    public List<ReaderDto> listReaders() {
        return readerService.listReaders();
    }

    @GetMapping("/{id}")
    @Operation(summary = "find a specific reader based on id")
    public ReaderDto findReaderById(@PathVariable("id") Long id) {
        return readerService.findReaderById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create new reader")
    @ApiResponse(responseCode = "201", description = "reader has been created")
    public ReaderDto createReader(@Valid @RequestBody CreateReaderCommand command) {
        return readerService.createReader(command);
    }

    @PutMapping("/{id}")
    @Operation(summary = "modify reader's data")
    public ReaderDto updateReader(@PathVariable("id") Long id, @Valid @RequestBody UpdateReaderCommand command) {
        return readerService.updateReader(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete reader")
    @ApiResponse(description = "reader has been deleted")
    public void deleteReader(@PathVariable("id") Long id) {
        readerService.deleteReader(id);
    }
}
