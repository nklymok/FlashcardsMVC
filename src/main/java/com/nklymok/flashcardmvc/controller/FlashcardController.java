package com.nklymok.flashcardmvc.controller;

import com.nklymok.flashcardmvc.model.Flashcard;
import com.nklymok.flashcardmvc.model.Test;
import com.nklymok.flashcardmvc.repository.TestRepository;
import com.nklymok.flashcardmvc.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("fcmvc")
@SessionAttributes("test")
public class FlashcardController {

    private final TestRepository testRepository;
    private final TestService testService;

    @Autowired
    public FlashcardController(TestRepository testRepository, TestService testService) {
        this.testRepository = testRepository;
        this.testService = testService;
    }

    @GetMapping
    public String getMenu() {
        return "index";
    }

    @GetMapping("build_test")
    public String buildTest(Model model) {
        Test test = new Test();
        test.setFlashcards(new ArrayList<>());
        for (int i = 0; i < 3; i++) {
            test.getFlashcards().add(new Flashcard());
        }

        model.addAttribute("test", test);
        return "build_test";
    }

    @GetMapping("pick_test")
    public String pickTest(Model model) {
        List<Test> tests = new ArrayList<>();
        testRepository.findAll().forEach(tests::add);
        model.addAttribute("tests", tests);
        return "pick_test";
    }

    @GetMapping("pick_test/{id}")
    public String pickTest(@PathVariable Long id, Model model) {
        Test test = testRepository.findById(id).get();
        testService.shuffleFlashcards(test);
        model.addAttribute("test", test);
        return "take_test";
    }

    @PostMapping("add_test")
    public String addTest(@ModelAttribute("test") Test test, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "build_test";
        }
        for(int i = 0; i < test.getFlashcards().size(); i++) {
            Flashcard f = test.getFlashcards().get(i);
            if (f == null || f.getQuestion() == null || f.getAnswer() == null ||
                    f.getQuestion().isBlank() || f.getAnswer().isBlank()) {
                test.getFlashcards().remove(f);
                i--;
                continue;
            }
            f.setTest(test);
        }
        testRepository.save(test);
        return "redirect:/fcmvc/pick_test";
    }

    @GetMapping("edit_test/{id}")
    public String editTest(@PathVariable Long id, Model model) {
        Test test = testRepository.findById(id).get();
        model.addAttribute("test", test);
        return "edit_test";
    }

    @PostMapping("check_test")
    public String checkTest(Test test, Model model) {
        List<Flashcard> incorrect = testService.getIncorrect(test);
        Integer totalCount = test.getFlashcards().size();
        Integer incorrectCount = incorrect.size();
        Integer correctCount = totalCount - incorrectCount;

        model.addAttribute("testName", test.getName());
        model.addAttribute("incorrectFlashcards", incorrect);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("incorrectCount", incorrectCount);
        model.addAttribute("correctCount", correctCount);
        model.addAttribute("testId", test.getId());
        return "test_result";
    }

}
