package com.example.aviasales2.service;

public interface SenderService {
    void buyEmail(Long userId, Long tripId, String list, int count);
    void buyEmailTour(Long userId);
}
