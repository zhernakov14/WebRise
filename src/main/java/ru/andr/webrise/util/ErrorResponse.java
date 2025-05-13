package ru.andr.webrise.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private int statusCode;
    private String error;
    private String message;
}

