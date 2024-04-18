package mk.ukim.finki.emtlab1.service;

import mk.ukim.finki.emtlab1.model.Author;
import mk.ukim.finki.emtlab1.model.Country;

import java.util.Optional;

public interface CountryService {
    Optional<Country> findById(Long id);

    Optional<Country> save(String name, String continent);
    Optional<Country> edit(Long id, String name, String continent);

    void deleteById(Long id);
}
