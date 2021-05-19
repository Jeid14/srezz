package com.srezz.modelDto;

import lombok.Value;

@Value
public class ErrorDto {
    String field;
    String value;
    String message;
}