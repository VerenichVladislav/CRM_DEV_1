package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {

    Wallet findById(long id);
}
