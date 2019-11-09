package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Wallet;
import com.example.aviasales2.entity.transferObjects.WalletDTO;
import com.example.aviasales2.repository.WalletRepository;
import com.example.aviasales2.service.IWalletService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletServiceImpl implements IWalletService {

    @Autowired
    WalletRepository walletRepository;
    @Autowired
    DozerBeanMapper mapper;

    @Override
    public Wallet findById(Integer id) {
        return walletRepository.findById(id);
    }

    @Override
    public List<WalletDTO> findAll(){return walletRepository.findAll().stream()
            .map(entity -> mapper.map(entity, WalletDTO.class))
            .collect(Collectors.toList());}
}
