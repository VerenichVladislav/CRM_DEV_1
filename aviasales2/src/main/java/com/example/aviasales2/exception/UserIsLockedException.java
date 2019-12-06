package com.example.aviasales2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserIsLockedException extends RuntimeException {
    private String message;
    private int status;
}
