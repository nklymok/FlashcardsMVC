package com.nklymok.flashcardmvc.controller;

import com.nklymok.flashcardmvc.exception.ReviewNotFoundException;
import com.nklymok.flashcardmvc.model.Flashcard;
import com.nklymok.flashcardmvc.model.Review;
import com.nklymok.flashcardmvc.repository.ReviewRepository;
import com.nklymok.flashcardmvc.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("fcmvc")
@SessionAttributes("review")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewRepository reviewRepository, ReviewService reviewService) {
        this.reviewRepository = reviewRepository;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String getMenu() {
        return "index";
    }

    @GetMapping("build_review")
    public String buildReview(Model model) {
        Review review = new Review("New Review", new ArrayList<>());
        for (int i = 0; i < 3; i++) {
            review.getFlashcards().add(new Flashcard());
        }
        model.addAttribute("review", review);
        return "build_review";
    }

    @GetMapping("pick_review")
    public String pickReview(Model model) {
        List<Review> reviews = new ArrayList<>();
        reviewRepository.findAll().forEach(reviews::add);
        model.addAttribute("reviews", reviews);
        return "pick_review";
    }

    @GetMapping("pick_review/{id}")
    public String pickReview(@PathVariable Long id, Model model) throws ReviewNotFoundException {
        Review review = reviewRepository.findById(id).orElseThrow(ReviewNotFoundException::new);
        reviewService.shuffleFlashcards(review);
        model.addAttribute("review", review);
        return "take_review";
    }

    @PostMapping("add_review")
    public String addReview(@ModelAttribute("review") @Valid Review review,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "build_review";
        }
        List <Flashcard> flashcards = review.getFlashcards();
        for (Flashcard f : flashcards) {
            f.setReview(review);
        }
        reviewRepository.save(review);
        return "redirect:/fcmvc/pick_review";
    }

    @GetMapping("build_review/{id}")
    public String editReview(@PathVariable Long id, Model model) throws ReviewNotFoundException {
        Review review = reviewRepository.findById(id).orElseThrow(ReviewNotFoundException::new);
        model.addAttribute("review", review);
        return "build_review";
    }

    @PostMapping("check_review")
    public String checkReview(Review review, Model model) {
        List<Flashcard> incorrect = reviewService.getIncorrect(review);
        model.addAttribute("review", review);
        model.addAttribute("incorrect", incorrect);
        return "review_result";
    }

    @GetMapping("delete_review/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewRepository.deleteById(id);
        return "redirect:/fcmvc/pick_review";
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public String pickReviewError() {
        return "review_not_found";
    }

}
