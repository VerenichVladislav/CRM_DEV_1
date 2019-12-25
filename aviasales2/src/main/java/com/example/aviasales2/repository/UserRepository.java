package com.example.aviasales2.repository;

import com.example.aviasales2.entity.User;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository <User, Integer>, QuerydslPredicateExecutor <User> {
    User findByUserId(Long id);

    User findByEmail(String email);

    void deleteByUserId(Long id);

    User findByUserName(String userName);

    List <User> findByLastNameAndEmail(String lastName, String email);

    List <User> findAllByLastName(String lastName);

    User findAllByUserName(String userName);

    User findByConfirmingHash(String confirmHash);
}
