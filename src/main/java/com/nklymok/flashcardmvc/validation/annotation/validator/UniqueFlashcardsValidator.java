package com.nklymok.flashcardmvc.validation.annotation.validator;

import com.nklymok.flashcardmvc.model.Flashcard;
import com.nklymok.flashcardmvc.validation.annotation.UniqueFlashcardsConstraint;
import org.thymeleaf.spring5.expression.Fields;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueFlashcardsValidator implements ConstraintValidator<UniqueFlashcardsConstraint, List<Flashcard>> {

    @Override
    public void initialize(UniqueFlashcardsConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(List<Flashcard> flashcards, ConstraintValidatorContext context) {
        boolean isValid = true;
        for (int i = 0; i < flashcards.size() - 1; i++) {
            for (int j = i + 1; j < flashcards.size(); j++) {
                if (flashcards.get(i).getQuestion().trim().
                        equalsIgnoreCase(flashcards.get(j).getQuestion().trim())) {
                    isValid = false;
                    break;
                }
            }
        }
        System.out.println(isValid ? "valid" : "not valid");
        return isValid;
    }
}
