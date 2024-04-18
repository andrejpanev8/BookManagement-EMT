package mk.ukim.finki.emtlab1.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data @Entity
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Author(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    private String name;

    private String surname;

    @ManyToOne (cascade = CascadeType.REMOVE)
    private Country country;
//
//    @OneToMany(mappedBy = "author")
//    private List<Kniga> books;
}
