package com.nklymok.flashcardmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Review Not Found")
public class ReviewNotFoundException extends Exception {

}
