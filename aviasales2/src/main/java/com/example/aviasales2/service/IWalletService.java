package com.example.aviasales2.service;


import com.example.aviasales2.entity.Wallet;

import java.math.BigDecimal;

public interface IWalletService {
    //Optional<Wallet> findById(Long id);
    Wallet findById(long id);
    void backMoney(BigDecimal price, long userId, long ticketId);
    BigDecimal getSum(long id);
    void pay(long userId,BigDecimal totalCost);
}
