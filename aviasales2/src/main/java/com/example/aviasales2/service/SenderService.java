package com.example.aviasales2.service;

public interface SenderService {
    void buyEmail(long userId, long tripId, String list, int count);
    void buyEmailTour(long userId);
}
