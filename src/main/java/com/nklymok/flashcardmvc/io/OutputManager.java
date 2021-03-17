package com.nklymok.flashcardmvc.io;

import com.nklymok.flashcardmvc.model.Flashcard;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class OutputManager {

    public static void outputFlashcards(List<Flashcard> flashcards, String folderName, String filename) {
        File directory = new File(folderName);
        File knownFile = new File(folderName + "/" + filename);
        BufferedWriter writer;

        if (!directory.exists()) {
            directory.mkdirs();
        }


        try {
            writer = new BufferedWriter(new FileWriter(knownFile));
            for (Flashcard f : flashcards) {
                writer.write(f.getAnswer() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void outputFlashcards(List<Flashcard> flashcards, String filename) {
        File knownFile = new File(filename);
        BufferedWriter writer;

        try {
            writer = new BufferedWriter(new FileWriter(knownFile));
            for (Flashcard f : flashcards) {
                writer.write(f.getAnswer() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
