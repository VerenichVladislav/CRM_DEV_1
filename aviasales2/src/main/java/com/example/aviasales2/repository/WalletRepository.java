package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Wallet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {

    List<Wallet> findAll();
    Wallet findById(long id);
    Wallet findByOwnerId(long id);
}
