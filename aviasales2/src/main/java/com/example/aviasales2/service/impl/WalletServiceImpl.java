package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Wallet;
import com.example.aviasales2.repository.WalletRepository;
import com.example.aviasales2.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements IWalletService {

    @Autowired
    WalletRepository walletRepository;

    @Override
    public Wallet findById(Integer id) {
        return walletRepository.findById(id);
    }
}
