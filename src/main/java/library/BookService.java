package library;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private ModelMapper modelMapper;
    private BookRepository repository;

    public List<BookDto> listBooks() {
        return repository.findAll().stream()
                .map(b -> modelMapper.map(b, BookDto.class))
                .collect(Collectors.toList());
    }

    public BookDto findBookById(Long id) {
        return modelMapper.map(repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book id cannot be found " + id)), BookDto.class);
    }

    public BookDto createBook(CreateBookCommand command) {
        return modelMapper.map(repository.save(new Book(
                command.getAuthor(),
                command.getTitle(),
                command.getRentalDate()
        )), BookDto.class);
    }

    @Transactional
    public BookDto updateBook(Long id, UpdateBookCommand command) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book id cannot be found" + id));
        book.setAuthor(command.getAuthor());
        book.setTitle(command.getTitle());
        book.setRentalDate(command.getRentalDate());
        repository.save(book);
        return modelMapper.map(book, BookDto.class);
    }

    public void deleteBook(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllBook() {
        repository.deleteAll();
    }
}
