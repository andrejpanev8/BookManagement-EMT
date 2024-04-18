package mk.ukim.finki.emtlab1.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emtlab1.model.Author;
import mk.ukim.finki.emtlab1.model.DTOs.KnigaDTO;
import mk.ukim.finki.emtlab1.model.Kniga;
import mk.ukim.finki.emtlab1.model.enumerations.CategoryType;
import mk.ukim.finki.emtlab1.repository.AuthorRepository;
import mk.ukim.finki.emtlab1.repository.LibraryRepository;
import mk.ukim.finki.emtlab1.service.BookService;
import org.springframework.boot.ssl.NoSuchSslBundleException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final LibraryRepository libraryRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<Kniga> findAll() {
        return libraryRepository.findAll();
    }

    @Override
    public Optional<Kniga> findById(Long id) {
        return libraryRepository.findById(id);
    }

    @Override
    public Optional<Kniga> findByName(String name) {
        return libraryRepository.findByName(name);
    }

    @Override
    public Optional<Kniga> findByAuthor(Long authorId)
    {
        Author author = authorRepository.findById(authorId).orElseThrow(NoSuchElementException::new);
        return libraryRepository.findByAuthor(author);
    }

    @Override
    @Transactional
    public Optional<Kniga> save(String name, CategoryType categoryType, Long authorId, Integer availableCopies) {
        Author author = authorRepository.findById(authorId).orElseThrow(NoSuchElementException::new);
        Kniga kniga = new Kniga(name,categoryType, author, availableCopies);
        return Optional.of(libraryRepository.save(kniga));
    }

    @Override
    @Transactional
    public Optional<Kniga> save(KnigaDTO knigaDTO) {
        Author author = authorRepository.findById(knigaDTO.getAuthorId()).orElseThrow(NoSuchElementException::new);
        Kniga kniga = new Kniga(knigaDTO.getName(), knigaDTO.getCategory(), author, knigaDTO.getAvailableCopies());
        return Optional.of(libraryRepository.save(kniga));
    }

    @Override
    @Transactional
    public Optional<Kniga> edit(Long id, String name, CategoryType categoryType, Long authorId, Integer availableCopies) {
        Author author = authorRepository.findById(authorId).orElseThrow(NoSuchElementException::new);
        Kniga kniga = libraryRepository.findById(id).orElseThrow(NoSuchElementException::new);

        kniga.setName(name);
        kniga.setCategory(categoryType);
        kniga.setAuthor(author);
        kniga.setAvailableCopies(availableCopies);

        return Optional.of(libraryRepository.save(kniga));
    }

    @Override
    @Transactional
    public Optional<Kniga> edit(Long id, KnigaDTO knigaDTO) {
        Kniga kniga = libraryRepository.findById(id).orElseThrow(NoSuchElementException::new);
        Author author = authorRepository.findById(knigaDTO.getAuthorId()).orElseThrow(NoSuchElementException::new);

        kniga.setName(knigaDTO.getName());
        kniga.setCategory(knigaDTO.getCategory());
        kniga.setAuthor(author);
        kniga.setAvailableCopies(knigaDTO.getAvailableCopies());

        return Optional.of(libraryRepository.save(kniga));
    }

    @Override
    public void deleteById(Long id) {
        libraryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Kniga> lend(Long id) {
        Kniga kniga = libraryRepository.findById(id).orElseThrow(NoSuchElementException::new);

        if(kniga.getAvailableCopies() > 0)
            kniga.setAvailableCopies(kniga.getAvailableCopies() - 1);
        return Optional.of(libraryRepository.save(kniga));
    }
}
