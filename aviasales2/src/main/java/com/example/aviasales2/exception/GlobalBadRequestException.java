package com.example.aviasales2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;

@Setter
@Getter
@AllArgsConstructor
public class GlobalBadRequestException extends RuntimeException {
    private BindingResult result;
}
