package com.example.aviasales2.service.impl;

import com.example.aviasales2.service.IWalletService;
import com.example.aviasales2.service.TripService;
import com.example.aviasales2.service.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationImpl implements Validation {
    @Autowired
    TripService tripService;
    @Autowired
    IWalletService walletService;
    @Override
    public Double checkSum(long userId,long tripId,int count){
        Double sum = tripService.getPrice(tripId)*count;
        Double userSum = walletService.getSum(userId);
        Double k;
        if(sum>userSum) {
            k=0.0;
        } else k=1.0;
        return k;
    }

    @Override
    public Double checkSeats(long tripId, int count) {
        int fullCountSeats = tripService.getFullCountSeats(tripId);
        Double k;
        if (fullCountSeats < count) {
            k=0.0;
        } else k=1.0;
        return k;
    }
}
