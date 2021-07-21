package org.patatesmaison.msreservation.controller;


import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msreservation.exception.APIException;
import org.patatesmaison.msreservation.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/test")
@Slf4j
public class Test {



    @PostConstruct
    public void logEnvSpecificValue() {
//        log.warn("---------------------------------------------");
//        log.warn("------------------------------ test : {}", test);
//        log.warn("---------------------------------------------");
    }


    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public String test() {
        log.warn("-------------- Test Recherche OK");
        return "Test Recherche OK";
    }




    @ExceptionHandler({APIException.class})
    public ResponseEntity<ErrorMessage> handleAPIException(APIException e) {
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), e.getHttpStatus());
    }
}
