package mk.ukim.finki.emtlab1.model;

import lombok.*;

import jakarta.persistence.*;


@Data @Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "library_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }

    private String name;
    private String role;
}
