package com.example.aviasales2.service.impl;

import com.example.aviasales2.service.WalletService;
import com.example.aviasales2.service.TripService;
import com.example.aviasales2.service.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ValidationImpl implements Validation {
    @Autowired
    TripService tripService;
    @Autowired
    WalletService walletService;

    @Override
    public int checkSum(long userId, long tripId, int count) {
        BigDecimal sum = tripService.calculateCost(count, tripId);
        BigDecimal userSum = walletService.getSum(userId);
        int k = userSum.compareTo(sum);
        if (k <= 0) {
            k = 0;
        } else k = 1;
        return k;
    }

    @Override
    public int checkSeats(long tripId, int count) {
        int fullCountSeats = tripService.getFullCountSeats(tripId);
        int k;
        if (fullCountSeats < count) {
            k = 0;
        } else k = 1;
        return k;
    }
}
