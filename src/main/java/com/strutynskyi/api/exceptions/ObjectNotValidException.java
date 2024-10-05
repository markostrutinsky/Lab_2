package com.strutynskyi.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
public class ObjectNotValidException extends RuntimeException {
    public ObjectNotValidException(String message) {
        super(message);
    }
}
