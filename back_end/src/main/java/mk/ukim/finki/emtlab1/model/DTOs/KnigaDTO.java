package mk.ukim.finki.emtlab1.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emtlab1.model.enumerations.CategoryType;

import jakarta.persistence.*;

@Data
@AllArgsConstructor @NoArgsConstructor
public class KnigaDTO {

    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryType category;

    private Long authorId;

    private Integer availableCopies;
}
