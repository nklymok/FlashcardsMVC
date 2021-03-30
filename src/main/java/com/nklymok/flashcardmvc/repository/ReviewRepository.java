package com.nklymok.flashcardmvc.repository;

import com.nklymok.flashcardmvc.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

}
