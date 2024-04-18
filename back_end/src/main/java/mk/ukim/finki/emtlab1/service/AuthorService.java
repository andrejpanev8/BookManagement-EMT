package mk.ukim.finki.emtlab1.service;

import mk.ukim.finki.emtlab1.model.Author;
import mk.ukim.finki.emtlab1.model.Country;
import mk.ukim.finki.emtlab1.model.DTOs.KnigaDTO;
import mk.ukim.finki.emtlab1.model.Kniga;
import mk.ukim.finki.emtlab1.model.enumerations.CategoryType;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findALl();
    Optional<Author> findById(Long id);

    Optional<Author> save(String name, String surname, Long CountryId);
    Optional<Author> edit(Long id, String name, String surname, Long CountryId);

    void deleteById(Long id);
}
