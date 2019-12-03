package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends CrudRepository <Wallet, Long> {

    List <Wallet> findAll();

    Wallet findByWalletId(Long id);

    Wallet findByOwnerUserId(Long id);
}
