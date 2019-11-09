package com.example.aviasales2.service;


import com.example.aviasales2.entity.Wallet;
import com.example.aviasales2.entity.transferObjects.WalletDTO;

import java.util.List;

public interface IWalletService {
    //Optional<Wallet> findById(Long id);
    Wallet findById(Integer id);
    List<WalletDTO> findAll();
}
