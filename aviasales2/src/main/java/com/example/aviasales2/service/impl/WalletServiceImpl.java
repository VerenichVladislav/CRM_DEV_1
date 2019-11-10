package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Ticket;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.entity.User;
import com.example.aviasales2.entity.Wallet;
import com.example.aviasales2.repository.UserRepository;
import com.example.aviasales2.repository.WalletRepository;
import com.example.aviasales2.service.IWalletService;
import com.example.aviasales2.service.TicketService;
import com.example.aviasales2.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class WalletServiceImpl implements IWalletService {

    @Autowired
    WalletRepository walletRepository;
    @Autowired
    TripService tripService;
    @Autowired
    TicketService ticketService;
    @Autowired
    UserRepository userRepository;

    @Override
    public Wallet findById(long id) {
        return walletRepository.findById(id); }

    @Override
    public void pay(long userId,BigDecimal totalCost) {
        User user = userRepository.findById(userId);
        Wallet userWallet = user.getWallet();
        Wallet adminWallet = walletRepository.findById(0);
        BigDecimal adminWalletSum = adminWallet.getSum();
        BigDecimal userWalletSum = userWallet.getSum();
        adminWalletSum=adminWalletSum.add(totalCost);
        userWalletSum=userWalletSum.subtract(totalCost);
        adminWallet.setSum(adminWalletSum);
        userWallet.setSum(userWalletSum);
    }

    @Override
    public BigDecimal getSum(long id) {
        User user = userRepository.findById(id);
        Wallet wallet = user.getWallet();
        BigDecimal userSum = wallet.getSum();
        return userSum;
    }

    @Override
    public void backMoney(BigDecimal price, long userId, long ticketId) {
        User user = userRepository.findById(userId);
        Wallet adminWallet = walletRepository.findById(0);
        Wallet userWallet = user.getWallet();
        BigDecimal adminWalletSum = adminWallet.getSum();
        BigDecimal userWalletSum = userWallet.getSum();
        adminWalletSum=adminWalletSum.subtract(price);
        userWalletSum=userWalletSum.add(price);
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
