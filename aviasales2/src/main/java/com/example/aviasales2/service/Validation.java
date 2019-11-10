package com.example.aviasales2.service;

public interface Validation {
    Double checkSum(long userId,long tripId,int count);
    Double checkSeats(long tripId, int count);
}
