package com.nklymok.flashcardmvc;

import com.nklymok.flashcardmvc.io.OutputManager;
import com.nklymok.flashcardmvc.model.Flashcard;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Component
public class FlashcardManager {

    public static void startFlashcards(List<Flashcard> flashcards, String folderName) {
        Scanner sc = new Scanner(System.in);
        List<Flashcard> incorrectFlashcards = new ArrayList<>();
        List<Flashcard> correctFlashcards = new ArrayList<>();
        String input;
        int passed = 0;

        Collections.shuffle(flashcards);
        for (Flashcard f : flashcards) {
            System.out.println(f.getQuestion());
            input = sc.nextLine();
            if (input.trim().equalsIgnoreCase("I want to stop")) {
                break;
            }
            f.setUserGuess(input);
            passed++;
            if (!input.trim().equalsIgnoreCase(f.getAnswer().trim())) {
                incorrectFlashcards.add(f);
            } else {
                correctFlashcards.add(f);
            }
        }

        finishFlashcards(incorrectFlashcards, correctFlashcards, passed, folderName);
    }

    public static void finishFlashcards(List<Flashcard> incorrect, List<Flashcard> correct, int total, String folderName) {
        int correctCount = total - incorrect.size();
        int incorrectCount = incorrect.size();

        System.out.printf("Total: %d\nCorrect: %d\nIncorrect: %d\n\n", total, correctCount, incorrectCount);
        if (incorrectCount == 0) return;
        System.out.println("Incorrect cards:\n");
        for (Flashcard f : incorrect) {
            System.out.println("Question: " + f.getQuestion());
            System.out.println("Answer: " + f.getAnswer());
            System.out.println("You typed: " + f.getUserGuess());
            System.out.println();
        }
        OutputManager.outputFlashcards(incorrect, folderName, "incorrect.txt");
        OutputManager.outputFlashcards(correct, folderName, "correct.txt");
    }

}
