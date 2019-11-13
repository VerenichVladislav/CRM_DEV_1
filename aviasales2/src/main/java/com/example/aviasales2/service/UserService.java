package com.example.aviasales2.service;

import com.example.aviasales2.entity.User;
import com.example.aviasales2.entity.transferObjects.UserDTO;

import java.util.List;

public interface UserService {
    User save(User user);
    User findById(Long id);
    List<User> findAll();
    List<User> findAllByLastName(String lastName);
    User findByEmail(String email);
    List<User> findByLastNameAndEmail(String lastName, String email);
    User findByUserName(String userName);
    void update(User user);
    void delete(User user);
    void deleteById(Integer id);
    User findByHashConfirm(String hashConfirm);
}
