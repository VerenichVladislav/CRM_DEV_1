package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Ticket;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.entity.User;
import com.example.aviasales2.entity.Wallet;
import com.example.aviasales2.entity.transferObjects.WalletDTO;
import com.example.aviasales2.repository.UserRepository;
import com.example.aviasales2.repository.WalletRepository;
import com.example.aviasales2.service.WalletService;
import com.example.aviasales2.service.TicketService;
import com.example.aviasales2.service.TripService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository walletRepository;
    @Autowired
    DozerBeanMapper mapper;
    @Autowired
    TripService tripService;
    @Autowired
    TicketService ticketService;
    @Autowired
    UserRepository userRepository;


    @Override

    public void pay(long userId, BigDecimal totalCost) {
        Wallet userWallet = walletRepository.findByOwnerId(userId);
        Wallet adminWallet = walletRepository.findById(0);
        adminWallet.setSum(adminWallet.getSum().add(totalCost));
        userWallet.setSum(userWallet.getSum().subtract(totalCost));
        walletRepository.saveAll(Arrays.asList(adminWallet,userWallet));
    }

    @Override
    public BigDecimal getSum(long id) {
        Wallet userWallet = walletRepository.findByOwnerId(id);
        BigDecimal userSum = userWallet.getSum();
        return userSum;
    }

    @Override
    public void backMoney(BigDecimal price, long userId, long ticketId) {
        Wallet adminWallet = walletRepository.findById(0);
        Wallet userWallet = walletRepository.findByOwnerId(userId);
        BigDecimal adminWalletSum = adminWallet.getSum();
        BigDecimal userWalletSum = userWallet.getSum();
        adminWalletSum = adminWalletSum.subtract(price);
        userWalletSum = userWalletSum.add(price);
        adminWallet.setSum(adminWalletSum);
        userWallet.setSum(userWalletSum);
        Ticket ticket = ticketService.findById(ticketId);
        long tripId = ticket.getTripId();
        Trip trip = tripService.findById(tripId);
        int k = trip.getFullCountSeats();
        k++;
        trip.setFullCountSeats(k);
    }

    @Override
    public Wallet findById(long id) {
        return walletRepository.findById(id);
    }

    @Override
    public List <WalletDTO> findAll() {
        return walletRepository.findAll().stream()
                .map(entity -> mapper.map(entity, WalletDTO.class))
                .collect(Collectors.toList());
    }
}
