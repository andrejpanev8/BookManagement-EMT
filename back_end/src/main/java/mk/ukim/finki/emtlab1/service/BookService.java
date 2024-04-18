package mk.ukim.finki.emtlab1.service;

import mk.ukim.finki.emtlab1.model.Author;
import mk.ukim.finki.emtlab1.model.DTOs.KnigaDTO;
import mk.ukim.finki.emtlab1.model.Kniga;
import mk.ukim.finki.emtlab1.model.enumerations.CategoryType;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Kniga> findAll();
    Optional<Kniga> findById(Long id);

    Optional<Kniga> findByName(String name);
    Optional<Kniga> findByAuthor(Long authorId);

    Optional<Kniga> save(String name, CategoryType categoryType, Long authorId, Integer availableCopies);
    Optional<Kniga> save(KnigaDTO knigaDTO);

    Optional<Kniga> edit(Long id, String name, CategoryType categoryType, Long authorId, Integer availableCopies);
    Optional<Kniga> edit(Long id, KnigaDTO knigaDTO);

    void deleteById(Long id);

    Optional<Kniga> lend(Long id);
}
