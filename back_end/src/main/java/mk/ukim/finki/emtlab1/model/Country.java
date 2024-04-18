package mk.ukim.finki.emtlab1.model;

import lombok.*;

import jakarta.persistence.*;


@Data
@Entity
@NoArgsConstructor
public class Country {
    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String continent;
}
