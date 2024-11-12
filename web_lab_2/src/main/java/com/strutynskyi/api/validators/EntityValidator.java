package com.strutynskyi.api.validators;

import com.strutynskyi.api.exceptions.ObjectNotValidException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class EntityValidator<T> {
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    public void validate(T objectDTO) {
        var violations = validator.validate(objectDTO);
        if (!violations.isEmpty()) {
            var errorMessages = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .toList();

            System.out.println(errorMessages);
            throw new ObjectNotValidException(errorMessages);
        }
    }
}
