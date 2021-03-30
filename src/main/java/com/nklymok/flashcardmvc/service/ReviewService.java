package com.nklymok.flashcardmvc.service;

import com.nklymok.flashcardmvc.model.Flashcard;
import com.nklymok.flashcardmvc.model.Review;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ReviewService {

    public void shuffleFlashcards(Review review) {
        Collections.shuffle(review.getFlashcards());
    }

    public List<Flashcard> getIncorrect(Review review) {
        List<Flashcard> flashcards = review.getFlashcards();
        List<Flashcard> result = new ArrayList<>();

        for (Flashcard f : flashcards) {
            if (f == null || f.getAnswer() == null || f.getQuestion() == null) continue;
                if (!f.getUserGuess().trim().equalsIgnoreCase(f.getAnswer().trim())) {
                    result.add(f);
                }
        }

        return result;
    }

}
