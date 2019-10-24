package com.example.aviasales2.service;

import com.example.aviasales2.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findById(Integer id);
    Iterable<User> findAll();
    List<User> findAllByLastName(String lastName);
    User findByEmail(String email);
    void update(User user);
    void delete(User user);
    void deleteById(Integer id);
}
