package dev.ruben.funkosspringval.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdNotValidException extends RuntimeException{
    public IdNotValidException(String message) {
        super(message);
    }
}
