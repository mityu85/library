package library.book;

import library.reader.Reader;
import library.reader.ReaderRepository;
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
    private BookRepository bookRepository;
    private ReaderRepository readerRepository;

    public List<BookDto> listBooks() {
        return bookRepository.findAll().stream()
                .map(b -> modelMapper.map(b, BookDto.class))
                .collect(Collectors.toList());
    }

    public BookDto findBookById(Long id) {
        return modelMapper.map(bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book id cannot be found " + id)), BookDto.class);
    }

    public BookDto createBook(CreateBookCommand command) {
        return modelMapper.map(bookRepository.save(new Book(
                command.getAuthor(),
                command.getTitle(),
                command.getRentalDate()
        )), BookDto.class);
    }

    public BookDto lendBook(Long bookId, Long readerId) {
        Reader reader = readerRepository.findById(readerId)
                .orElseThrow(() -> new IllegalArgumentException("Reader id cannot be found " + readerId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book id cannot be found " + bookId));
        book.setReader(reader);
        bookRepository.save(book);
        return modelMapper.map(book, BookDto.class);
    }

    @Transactional
    public BookDto updateBook(Long id, UpdateBookCommand command) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book id cannot be found" + id));
        book.setAuthor(command.getAuthor());
        book.setTitle(command.getTitle());
        book.setRentalDate(command.getRentalDate());
        bookRepository.save(book);
        return modelMapper.map(book, BookDto.class);
    }

    @Transactional
    public BookDto updateLentBook(Long bookId, Long readerId, UpdateBookCommand command) {
        Reader reader = readerRepository.findById(readerId)
                .orElseThrow(() -> new IllegalArgumentException("Reader id cannot be found " + readerId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book id cannot be found" + bookId));
        book.setAuthor(command.getAuthor());
        book.setTitle(command.getTitle());
        book.setRentalDate(command.getRentalDate());
        book.setReader(reader);
        bookRepository.save(book);
        return modelMapper.map(book, BookDto.class);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public void deleteAllBook() {
        bookRepository.deleteAll();
    }

}
