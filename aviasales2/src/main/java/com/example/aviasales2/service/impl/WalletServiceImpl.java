package com.example.aviasales2.service.impl;

import com.example.aviasales2.Constants;
import com.example.aviasales2.PersonRequest;
import com.example.aviasales2.entity.Sender;
import com.example.aviasales2.entity.Ticket;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.entity.Wallet;
import com.example.aviasales2.entity.transferObjects.WalletDTO;
import com.example.aviasales2.repository.WalletRepository;
import com.example.aviasales2.service.SenderService;
import com.example.aviasales2.service.TicketService;
import com.example.aviasales2.service.TripService;
import com.example.aviasales2.service.WalletService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final DozerBeanMapper mapper;
    private final TripService tripService;
    private final TicketService ticketService;
    private final SenderService senderService;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, DozerBeanMapper mapper, TripService tripService, TicketService ticketService, SenderService senderService) {
        this.walletRepository = walletRepository;
        this.mapper = mapper;
        this.tripService = tripService;
        this.ticketService = ticketService;
        this.senderService = senderService;
    }

    @Transactional
    @Override
    public void pay(long userId, long tripId, int count, List <PersonRequest> passengers) {
        BigDecimal totalCost = tripService.calculateCost(count, tripId);
        String list = ticketService.save(userId, tripId, count, passengers);
        senderService.buyEmail(userId, tripId, list, count);
        Wallet userWallet = walletRepository.findByOwnerUserId(userId);
        Wallet adminWallet = walletRepository.findByWalletId(0L);
        adminWallet.setSum(adminWallet.getSum().add(totalCost));
        userWallet.setSum(userWallet.getSum().subtract(totalCost));
        walletRepository.saveAll(Arrays.asList(adminWallet, userWallet));
    }

    @Override
    public BigDecimal getSum(long id) {
        Wallet userWallet = walletRepository.findByOwnerUserId(id);
        BigDecimal userSum = userWallet.getSum();
        return userSum;
    }

    @Override
    public void backMoney(BigDecimal price, long userId, long ticketId) {
        Wallet adminWallet = walletRepository.findByWalletId(0L);
        Wallet userWallet = walletRepository.findByOwnerUserId(userId);
        BigDecimal adminWalletSum = adminWallet.getSum();
        BigDecimal userWalletSum = userWallet.getSum();
        adminWalletSum = adminWalletSum.subtract(price);
        userWalletSum = userWalletSum.add(price);
        adminWallet.setSum(adminWalletSum);
        userWallet.setSum(userWalletSum);
        Ticket ticket = ticketService.findByTicketId(ticketId);
        long tripId = ticket.getTripId();
        Trip trip = tripService.findById(tripId);
        int k = trip.getFullCountSeats();
        k++;
        trip.setFullCountSeats(k);
    }

    @Override
    public Wallet findById(long id) {
        return walletRepository.findByWalletId(id);
    }

    @Override
    public List <WalletDTO> findAll() {
        return walletRepository.findAll().stream()
                .map(entity -> mapper.map(entity, WalletDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity <String> confirm(Long userId, String hashConfirm) {
        if (walletRepository.findByOwnerUserId(userId).getStatus().equals("AWAITING")) {
            String[] strArr = new String[2];
            String delimeter = ";";
            byte[] decodedBytes = Base64.getUrlDecoder().decode(hashConfirm);
            String hash = new String(decodedBytes);
            strArr = hash.split(delimeter);
            double sum = Double.valueOf(strArr[1]);
            walletRepository.findByOwnerUserId(userId).setSum(walletRepository.findByOwnerUserId(userId).getSum().add(BigDecimal.valueOf(sum)));
            walletRepository.findByOwnerUserId(userId).setStatus("OK");
            walletRepository.save(walletRepository.findByOwnerUserId(userId));
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Good! You replenish your wallet!");
        } else return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body("Oh no, we have a problem! This link has already been used!");
    }

    @Override
    public void sendConfirmToEmail(Long userId, double sum) {
        Wallet wallet = walletRepository.findByOwnerUserId(userId);
        BigDecimal newSum = BigDecimal.valueOf(sum);
        Sender sender = new Sender();
        String subject = "Пополнение кошелька";
        String hash = Base64.getUrlEncoder().encodeToString((userId.toString() + ";" + (newSum.toString())).getBytes());
        String url = Constants.URL + "wallets/" + userId + "/confirm/" + hash;
        String text = "Добрый день " + walletRepository.findByOwnerUserId(userId).getOwner().getUserName() + "! Это очень важное письмо пришло что бы выподтвердили пополнение счёта на нашем супер сайте нажмите на эту ссылку " + url;
        sender.send(subject, text, walletRepository.findByOwnerUserId(userId).getOwner().getEmail());
        walletRepository.findByOwnerUserId(userId).setStatus("AWAITING");
        walletRepository.save(walletRepository.findByOwnerUserId(userId));
    }
}