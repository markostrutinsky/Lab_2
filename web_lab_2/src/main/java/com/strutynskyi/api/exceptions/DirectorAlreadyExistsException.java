package com.strutynskyi.api.exceptions;

public class DirectorAlreadyExistsException extends RuntimeException {
    private final static String MESSAGE = "Director already exists and has id: ";
    public DirectorAlreadyExistsException(Long id) {
        super(MESSAGE + id);
    }
}
