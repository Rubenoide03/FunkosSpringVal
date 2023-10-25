package dev.ruben.funkosspringval.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FunkoNotAvailableAddException extends RuntimeException{
    public FunkoNotAvailableAddException(String message) {
        super(message);
    }
}
