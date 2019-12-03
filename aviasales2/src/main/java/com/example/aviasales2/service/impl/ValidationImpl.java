package com.example.aviasales2.service.impl;

import com.example.aviasales2.service.TripService;
import com.example.aviasales2.service.Validation;
import com.example.aviasales2.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ValidationImpl implements Validation {
    private final TripService tripService;
    private final WalletService walletService;

    @Autowired
    public ValidationImpl(TripService tripService, WalletService walletService) {
        this.tripService = tripService;
        this.walletService = walletService;
    }

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
