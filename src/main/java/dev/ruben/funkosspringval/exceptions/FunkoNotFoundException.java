package dev.ruben.funkosspringval.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FunkoNotFoundException extends RuntimeException{
    public FunkoNotFoundException(String message) {
        super(message);
    }
}
