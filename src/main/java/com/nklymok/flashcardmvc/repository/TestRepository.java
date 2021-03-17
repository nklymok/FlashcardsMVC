package com.nklymok.flashcardmvc.repository;

import com.nklymok.flashcardmvc.model.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends CrudRepository<Test, Long> {

}
