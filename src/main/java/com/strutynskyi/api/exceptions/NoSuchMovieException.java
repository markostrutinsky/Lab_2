package com.strutynskyi.api.exceptions;

public class NoSuchMovieException extends RuntimeException {
    private final static String MESSAGE = "No such movie with id: ";
    public NoSuchMovieException(Long id) {
        super(MESSAGE + id);
    }
}
