package com.strutynskyi.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ObjectNotValidException extends RuntimeException {
    private List<String> errorMessages;
}
