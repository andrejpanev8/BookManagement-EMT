package mk.ukim.finki.emtlab1.repository;

import mk.ukim.finki.emtlab1.model.Author;
import mk.ukim.finki.emtlab1.model.Kniga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<Kniga, Long> {
    Optional<Kniga> findByName(String name);
    Optional<Kniga> findByAuthor(Author author);
}
