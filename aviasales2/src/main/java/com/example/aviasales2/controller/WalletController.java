package com.example.aviasales2.controller;

import com.example.aviasales2.entity.transferObjects.WalletDTO;
import com.example.aviasales2.service.WalletService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 10000)
@RestController
@RequestMapping("/wallets")
public class WalletController {

    private final WalletService walletService;
    private final DozerBeanMapper mapper;

    @Autowired
    public WalletController(WalletService walletService, DozerBeanMapper mapper) {
        this.walletService = walletService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public WalletDTO findById(@PathVariable(name = "id") Long id) {
        return mapper.map(walletService.findById(id), WalletDTO.class);
    }

    @GetMapping("/{user_id}/sendConfirm")
    public void sendConfirmToEmail(@PathVariable(name = "user_id") Long id,
                                   @RequestParam double sum) throws MessagingException {
        walletService.sendConfirmToEmail(id, sum);
    }

    @GetMapping("{userId}/confirm/{hashConfirm}")
    public void confirm(@PathVariable(name = "userId") Long userId,
                                           @PathVariable(name = "hashConfirm") String hashConfirm) throws Exception {
        walletService.confirm(userId, hashConfirm);
    }
}
