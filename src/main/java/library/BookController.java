package library;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/library")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> listBooks() {
        return bookService.listBooks();
    }

    @GetMapping("/{id}")
    public BookDto findBookById(@PathVariable("id") Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@Valid @RequestBody CreateBookCommand command) {
        return bookService.createBook(command);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable("id") Long id, @Valid @RequestBody UpdateBookCommand command) {
        return bookService.updateBook(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
}
