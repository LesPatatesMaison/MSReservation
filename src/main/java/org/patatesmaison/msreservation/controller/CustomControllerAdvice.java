package org.patatesmaison.msreservation.controller;

import org.patatesmaison.msreservation.exception.APIException;
import org.patatesmaison.msreservation.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            IllegalStateException.class,
            InvalidMediaTypeException.class,
            MethodArgumentTypeMismatchException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<ErrorMessage> handleHttpMessageNotReadableException(Exception e) {
        return new ResponseEntity<>(new ErrorMessage("Requete mal formee - date invalide?"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({APIException.class})
    public ResponseEntity<ErrorMessage> handleAPIException(APIException e) {
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), e.getHttpStatus());
    }
}
