package com.example.aviasales2.service;


import com.example.aviasales2.entity.Wallet;
import com.example.aviasales2.entity.transferObjects.WalletDTO;

import java.math.BigDecimal;
import java.util.List;

public interface IWalletService {

    Wallet findById(long id);
    List<WalletDTO> findAll();
    void backMoney(BigDecimal price, long userId, long ticketId);
    BigDecimal getSum(long id);
    void pay(long userId,BigDecimal totalCost);
}
