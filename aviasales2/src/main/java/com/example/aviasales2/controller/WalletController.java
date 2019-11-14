package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Wallet;
import com.example.aviasales2.entity.transferObjects.UserDTO;
import com.example.aviasales2.entity.transferObjects.WalletDTO;
import com.example.aviasales2.service.WalletService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 10000)
@RestController
@RequestMapping("/wallets")
public class WalletController {
    @Autowired
    private WalletService walletService;
    @Autowired
    private DozerBeanMapper mapper;

    @GetMapping("/{id}")
    public WalletDTO findById(@PathVariable(name = "id") Long id) {
        return mapper.map(walletService.findById(id), WalletDTO.class);
    }
}
