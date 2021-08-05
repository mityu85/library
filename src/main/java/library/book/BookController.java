package library.book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book controller layer", description = "This layer responsible for control the operations on Book entity")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Operation(summary = "list all books in the library")
    public List<BookDto> listBooks() {
        return bookService.listBooks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "find a specific book based on id")
    public BookDto findBookById(@PathVariable("id") Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@Valid @RequestBody CreateBookCommand command) {
        return bookService.createBook(command);
    }

    @PostMapping("{bookId}/reader/{readerId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "assign an exists book for a reader")
    @ApiResponse(responseCode = "201", description = "Book has been rented")
    public BookDto lendBook(@PathVariable("bookId") Long bookId, @PathVariable("readerId") Long readerId) {
        return bookService.lendBook(bookId, readerId);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update a specific book based on id")
    public BookDto updateBook(@PathVariable("id") Long id, @Valid @RequestBody UpdateBookCommand command) {
        return bookService.updateBook(id, command);
    }

    @PutMapping("/{bookId}/reader/{readerId}")
    @Operation(summary = "update a rented book based on id")
    public BookDto updateLentBook(@PathVariable("bookId") Long bookId, @PathVariable("readerId") Long readerId, @Valid @RequestBody UpdateBookCommand command) {
        return bookService.updateLentBook(bookId, readerId, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete a specific book based on id")
    @ApiResponse(description = "book has been deleted")
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
}
