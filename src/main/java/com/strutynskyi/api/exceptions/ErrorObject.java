package com.strutynskyi.api.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorObject {
    private HttpStatus status;
    private String message;
}
