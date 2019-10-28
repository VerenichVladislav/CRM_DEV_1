package com.example.aviasales2.service;

import com.example.aviasales2.entity.User;
import com.example.aviasales2.entity.Wallet;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    User findById(Long id);
    Iterable<User> findAll();
    List<User> findAllByLastName(String lastName);
    User findByEmail(String email);
    List<User> findByLastNameAndEmail(String lastName, String email);
    void update(User user);
    void delete(User user);
    void deleteById(Integer id);

}
