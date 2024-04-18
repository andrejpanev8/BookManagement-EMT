package mk.ukim.finki.emtlab1;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emtlab1.model.Author;
import mk.ukim.finki.emtlab1.model.Country;
import mk.ukim.finki.emtlab1.model.DTOs.KnigaDTO;
import mk.ukim.finki.emtlab1.model.enumerations.CategoryType;
import mk.ukim.finki.emtlab1.service.AuthorService;
import mk.ukim.finki.emtlab1.service.BookService;
import mk.ukim.finki.emtlab1.service.CountryService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class LoadDatabase {
    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    @PostConstruct
    public void initData() {
        Optional<Country> c1 = this.countryService.save("Finland", "Europe");
        Optional<Country> c2 = this.countryService.save("Denmark", "Europe");
        Optional<Country> c3 = this.countryService.save("Macedonia", "Europe");
        Optional<Country> c4 = this.countryService.save("USA", "North America");
        Optional<Country> c5 = this.countryService.save("Great Britain", "Europe");

        Optional<Author> a1 = this.authorService.save("William", "Shakespeare", c1.get().getId());
        Optional<Author> a2 = this.authorService.save("Danielle", "Steel", c4.get().getId());
        Optional<Author> a3 = this.authorService.save("J. K.", "Rowling", c5.get().getId());
        Optional<Author> a4 = this.authorService.save("Agatha", "Christie", c5.get().getId());

        bookService.save(new KnigaDTO("Hamlet", CategoryType.DRAMA, a1.get().getId(), 10));
        bookService.save(new KnigaDTO("War and peace", CategoryType.NOVEL, a2.get().getId(), 15));
        bookService.save(new KnigaDTO("Harry Potter", CategoryType.FANTASY, a3.get().getId(), 13));
        bookService.save(new KnigaDTO("Miss Marple", CategoryType.HISTORY, a4.get().getId(), 25));

    }
}