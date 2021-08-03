package library;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
