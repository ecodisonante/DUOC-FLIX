package com.fullstack.duocflix.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fullstack.duocflix.model.ExceptionResponse;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> notFoundUri() {
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), "Ooops!! Creo que te perdiste."));
    }
}
