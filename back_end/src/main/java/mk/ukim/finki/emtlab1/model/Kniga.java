package mk.ukim.finki.emtlab1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.emtlab1.model.enumerations.CategoryType;

@Data @Entity
@Getter @Setter
@NoArgsConstructor
public class Kniga {

    public Kniga(String name, CategoryType category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryType category;

    @ManyToOne
    private Author author;

    private Integer availableCopies;
}
