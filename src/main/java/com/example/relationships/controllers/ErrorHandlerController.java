package com.example.relationships.controllers;

import com.example.relationships.exceptions.BadRequest;
import com.example.relationships.exceptions.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorHandlerController {

    @ExceptionHandler(BadRequest.class)
    public ProblemDetail handleBadRequest(BadRequest ex) {
        log.error(ex.getMessage(),ex);
        return ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFound.class)
    public ProblemDetail handleNotFoundException(NotFound ex) {
        log.error(ex.getMessage(),ex);
        return ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail handleRuntimeException(RuntimeException ex) {
        log.error(ex.getMessage(),ex);
        return ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
