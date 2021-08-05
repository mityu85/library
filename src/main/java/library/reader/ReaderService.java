package library.reader;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReaderService {

    private ModelMapper modelMapper;
    private ReaderRepository readerRepository;

    public List<ReaderDto> listReaders() {
        return readerRepository.findAll().stream()
                .map(r -> modelMapper.map(r, ReaderDto.class))
                .collect(Collectors.toList());
    }

    public ReaderDto findReaderById(Long id) {
        return modelMapper.map(readerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reader id cannot be found " + id)), ReaderDto.class);
    }

    public ReaderDto createReader(CreateReaderCommand command) {
        return modelMapper.map(readerRepository.save(new Reader(
                command.getName(),
                command.getAddress()
        )), ReaderDto.class);
    }

    @Transactional
    public ReaderDto updateReader(Long id, UpdateReaderCommand command) {
        Reader reader = readerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reader id cannot found " + id));
        reader.setName(command.getName());
        reader.setAddress(command.getAddress());
        readerRepository.save(reader);
        return modelMapper.map(reader, ReaderDto.class);
    }

    public void deleteReader(Long id) {
        readerRepository.deleteById(id);
    }

    public void deleteAllReader() {
        readerRepository.deleteAll();
    }
}
