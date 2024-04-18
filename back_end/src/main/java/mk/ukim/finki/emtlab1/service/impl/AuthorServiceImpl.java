package mk.ukim.finki.emtlab1.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emtlab1.model.Author;
import mk.ukim.finki.emtlab1.model.Country;
import mk.ukim.finki.emtlab1.repository.AuthorRepository;
import mk.ukim.finki.emtlab1.repository.CountryRepository;
import mk.ukim.finki.emtlab1.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    AuthorRepository authorRepository;
    CountryRepository countryRepository;

    @Override
    public List<Author> findALl() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Transactional
    @Override
    public Optional<Author> save(String name, String surname, Long CountryId) {
        Country country = countryRepository.findById(CountryId).orElseThrow(NoSuchElementException::new);
        Author author = new Author(name,surname,country);
        return Optional.of(authorRepository.save(author));
    }

    @Transactional
    @Override
    public Optional<Author> edit(Long id, String name, String surname, Long CountryId) {
        Country country = countryRepository.findById(CountryId).orElseThrow(NoSuchElementException::new);
        Author author = authorRepository.findById(id).orElseThrow(NoSuchElementException::new);

        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);

        return Optional.of(authorRepository.save(author));
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
