package com.example.aviasales2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class NoSuchEntityException extends RuntimeException {
    private Class aClass;
}
