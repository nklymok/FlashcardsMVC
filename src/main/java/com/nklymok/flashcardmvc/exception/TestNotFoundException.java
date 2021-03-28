package com.nklymok.flashcardmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Test Not Found")
public class TestNotFoundException extends Exception {

}
