package com.nklymok.flashcardmvc.io;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class InputManager {

    public static List<String> readLines(String filename) {
        List<String> result = new ArrayList<>();
        File inputFile = new File(filename);
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            result = reader.lines().filter(not(String::isBlank)).map(String :: trim).collect(Collectors.toList());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean checkInput(List<String> questions, List<String> answers, String[] args) {
        return questions != null && answers != null && questions.size() == answers.size() && args.length == 1;
    }

    public static <R> Predicate<R> not(Predicate<R> predicate) {
        return predicate.negate();
    }
}
