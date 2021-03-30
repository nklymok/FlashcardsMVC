package com.nklymok.flashcardmvc.validation.annotation;

import com.nklymok.flashcardmvc.validation.annotation.validator.UniqueFlashcardsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueFlashcardsValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueFlashcardsConstraint {
    String message() default "Flashcards not unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
