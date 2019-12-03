package com.example.aviasales2.service;

public interface Validation {
    int checkSum(long userId, long tripId, int count);

    int checkSeats(long tripId, int count);
}
