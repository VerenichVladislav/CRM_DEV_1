package com.example.aviasales2.service;


import com.example.aviasales2.entity.Wallet;

public interface IWalletService {
    //Optional<Wallet> findById(Long id);
    Wallet findById(long id);
    void backMoney(Double price, long userId, long ticketId);
    Double getSum(long id);
    void pay(long userId, long tripId, int count);
}
