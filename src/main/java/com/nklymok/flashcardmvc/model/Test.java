package com.nklymok.flashcardmvc.model;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Entity
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
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

    public Test() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Flashcard> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(List<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }
}
