package com.example.aviasales2.service;


import com.example.aviasales2.PersonRequest;
import com.example.aviasales2.entity.TourRequest;
import com.example.aviasales2.entity.Wallet;
import com.example.aviasales2.entity.transferObjects.WalletDTO;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface WalletService {

    Wallet findById(long id);

    List <WalletDTO> findAll();

    void backMoney(BigDecimal price, long userId, long ticketId);

    BigDecimal getSum(long id);

    void pay(long userId, long tripId, int count, List <PersonRequest> passengers);

    void payForTour(TourRequest tourRequest);

    void sendConfirmToEmail(Long userId, double sum);

    void confirm(Long userId, String hashConfirm) throws Exception;
}
