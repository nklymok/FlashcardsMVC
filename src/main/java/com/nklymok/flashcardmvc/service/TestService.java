package com.nklymok.flashcardmvc.service;

import com.nklymok.flashcardmvc.model.Flashcard;
import com.nklymok.flashcardmvc.model.Test;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    public List<Flashcard> getIncorrect(Test test) {
        List<Flashcard> flashcards = test.getFlashcards();
        List<Flashcard> result = new ArrayList<>();

        for (Flashcard f : flashcards) {
                if (!f.getUserGuess().trim().equalsIgnoreCase(f.getAnswer().trim())) {
                    result.add(f);
                }
        }

        return result;
    }

}
