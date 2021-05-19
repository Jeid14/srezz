package com.srezz.exception;

import com.srezz.modelDto.ErrorDto;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InvalidInputDataException extends RuntimeException {

    @Getter
    private final List<ErrorDto> errors;

    public InvalidInputDataException(ErrorDto error) {
        this.errors = Collections.singletonList(error);
    }

    public InvalidInputDataException(BindingResult bindingResult) {
        this.errors = bindingResult.getAllErrors().stream()
                .map(error -> (FieldError) error)
                .map(error -> new ErrorDto(error.getField(), String.valueOf(error.getRejectedValue()), error.getDefaultMessage()))
                .collect(Collectors.toList());
    }
}