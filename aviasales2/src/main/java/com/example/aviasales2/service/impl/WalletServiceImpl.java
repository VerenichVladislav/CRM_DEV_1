package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Ticket;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.entity.Wallet;
import com.example.aviasales2.repository.WalletRepository;
import com.example.aviasales2.service.IWalletService;
import com.example.aviasales2.service.TicketService;
import com.example.aviasales2.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements IWalletService {

    @Autowired
    WalletRepository walletRepository;
    @Autowired
    TripService tripService;
    @Autowired
    TicketService ticketService;

    @Override
    public Wallet findById(long id) {
        return walletRepository.findById(id); }

    @Override
    public void pay(long userId, long tripId, int count) {
        Double price = tripService.getPrice(tripId);
        Wallet adminWallet = walletRepository.findById(0);
        Wallet userWallet = walletRepository.findById(userId);
        Double adminWalletSum = adminWallet.getSum();
        Double userWalletSum = userWallet.getSum();
        adminWalletSum+=price*count;
        userWalletSum-=price*count;
        adminWallet.setSum(adminWalletSum);
        userWallet.setSum(userWalletSum);
    }

    @Override
    public Double getSum(long id) {
        Wallet wallet = walletRepository.findById(id);
        Double userSum = wallet.getSum();
        return userSum;
    }

    @Override
    public void backMoney(Double price, long userId, long ticketId) {
        Wallet adminWallet = walletRepository.findById(0);
        Wallet userWallet = walletRepository.findById(userId);
        Double adminWalletSum = adminWallet.getSum();
        Double userWalletSum = userWallet.getSum();
        adminWalletSum+=price;
        userWalletSum-=price;
        adminWallet.setSum(adminWalletSum);
        userWallet.setSum(userWalletSum);
        Ticket ticket = ticketService.findById(ticketId);
        long tripId = ticket.getTripId();
        Trip trip = tripService.findById(tripId);
        int k = trip.getFullCountSeats();
        k++;
        trip.setFullCountSeats(k);
    }
}
