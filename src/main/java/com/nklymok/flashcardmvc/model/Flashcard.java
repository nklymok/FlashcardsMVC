package com.nklymok.flashcardmvc.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "flashcard")
@Data
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question")
    @NotBlank(message = "question is required")
    private String question;

    @Column(name = "answer")
    @NotBlank(message = "answer is required")
    private String answer;

    @Transient
    private String userGuess;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    public Flashcard(Long id, String question, String answer, String userGuess) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.userGuess = userGuess;
    }

    public Flashcard() {

    }
}
