package com.example.aviasales2.service;


import com.example.aviasales2.entity.Wallet;

public interface IWalletService {
    //Optional<Wallet> findById(Long id);
    Wallet findById(Integer id);
}
