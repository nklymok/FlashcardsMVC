package com.nklymok.flashcardmvc.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "test")
@Data
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "test name is required")
    private String name;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    @Valid
    private List<Flashcard> flashcards;

    public Test(Long id, String name, List<Flashcard> flashcards) {
        this.id = id;
        this.name = name;
        this.flashcards = flashcards;
    }

    public Test(String name, List<Flashcard> flashcards) {
        this.name = name;
        this.flashcards = flashcards;
    }

    public Test() { }
}
