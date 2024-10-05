package com.strutynskyi.api.handler;


import com.strutynskyi.api.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    private static final Map<Class<? extends Exception>, HttpStatus> exceptionStatusMap = Map.of(
            DirectorAlreadyExistsException.class, HttpStatus.CONFLICT,
            MovieAlreadyExistsException.class, HttpStatus.CONFLICT,
            NoSuchDirectorException.class, HttpStatus.NOT_FOUND,
            NoSuchMovieException.class, HttpStatus.NOT_FOUND,
            ObjectNotValidException.class, HttpStatus.BAD_REQUEST
    );

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleException(Exception ex) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage(ex.getMessage());

        HttpStatus status = exceptionStatusMap.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        errorObject.setStatus(status);

        return new ResponseEntity<>(errorObject, status);
    }/*
    @ExceptionHandler(DirectorAlreadyExistsException.class)
    public ResponseEntity<ErrorObject> handleException(DirectorAlreadyExistsException ex){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage(ex.getMessage());
        errorObject.setStatus(HttpStatus.CONFLICT);

        return new ResponseEntity<>(errorObject, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoSuchDirectorException.class)
    public ResponseEntity<?> handleException(NoSuchDirectorException ex){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage(ex.getMessage());
        errorObject.setStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MovieAlreadyExistsException.class)
    public ResponseEntity<?> handleException(MovieAlreadyExistsException ex){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage(ex.getMessage());
        errorObject.setStatus(HttpStatus.CONFLICT);

        return new ResponseEntity<>(errorObject, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoSuchMovieException.class)
    public ResponseEntity<?> handleException(NoSuchMovieException ex){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage(ex.getMessage());
        errorObject.setStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ObjectNotValidException.class)
    public ResponseEntity<?> handleException(ObjectNotValidException ex){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage(ex.getMessage());
        errorObject.setStatus(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }*/
}
