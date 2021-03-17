package com.nklymok.flashcardmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

@SpringBootApplication
public class FlashcardApp extends WebMvcAutoConfiguration {
    public static void main(String[] args) {
        SpringApplication.run(FlashcardApp.class,args);
    }
}
