package com.nklymok.flashcardmvc.model;

import com.nklymok.flashcardmvc.validation.annotation.UniqueFlashcardsConstraint;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "review")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "review name is required")
    private String name;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    @Valid
    @UniqueFlashcardsConstraint
    private List<Flashcard> flashcards;

    public Review(String name, List<Flashcard> flashcards) {
        this.name = name;
        this.flashcards = flashcards;
    }

    public Review() { }
}
