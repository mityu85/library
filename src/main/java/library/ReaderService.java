package library;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReaderService {

    private ModelMapper modelMapper;
    private ReaderRepository repository;
}
