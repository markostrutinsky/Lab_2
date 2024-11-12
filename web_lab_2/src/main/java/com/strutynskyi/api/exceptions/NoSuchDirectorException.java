package com.strutynskyi.api.exceptions;

public class NoSuchDirectorException extends RuntimeException {
    private static final String MESSAGE = "No such director with id: ";
    public NoSuchDirectorException(Long id) {
        super(MESSAGE + id);
    }
}
