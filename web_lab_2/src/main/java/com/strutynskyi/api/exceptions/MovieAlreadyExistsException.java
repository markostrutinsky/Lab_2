package com.strutynskyi.api.exceptions;

public class MovieAlreadyExistsException extends RuntimeException {
    private final static String MESSAGE = "Movie already exists and has id: ";
    public MovieAlreadyExistsException(Long id) {
        super(MESSAGE + id);
    }
}
