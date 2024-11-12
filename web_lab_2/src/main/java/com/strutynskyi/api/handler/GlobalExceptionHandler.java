package com.strutynskyi.api.handler;


import com.strutynskyi.api.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DirectorAlreadyExistsException.class)
    public ResponseEntity<?> handleException(DirectorAlreadyExistsException ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchDirectorException.class)
    public ResponseEntity<?> handleException(NoSuchDirectorException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(MovieAlreadyExistsException.class)
    public ResponseEntity<?> handleException(MovieAlreadyExistsException ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchMovieException.class)
    public ResponseEntity<?> handleException(NoSuchMovieException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(ObjectNotValidException.class)
    public ResponseEntity<?> handleException(ObjectNotValidException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getErrorMessages());
    }
}
