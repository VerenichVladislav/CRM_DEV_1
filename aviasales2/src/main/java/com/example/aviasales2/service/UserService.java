package com.example.aviasales2.service;

import com.example.aviasales2.config.filterConfig.UserFilter;
import com.example.aviasales2.entity.User;
import com.example.aviasales2.entity.transferObjects.UserDTO;

import java.util.List;

public interface UserService {
    User save(User user);

    User findById(Long id);

    List <User> findAll(UserFilter userFilter);

    List <User> findAllByLastName(String lastName);

    User findByEmail(String email);

    List <User> findByLastNameAndEmail(String lastName, String email);

    User findByUserName(String userName);

    void sendPasswordToEmail(String userName);

    void update(User user);

    void delete(User user);

    void deleteById(Long id);

    User findByHashConfirm(String hashConfirm);

    void lockUser(Long userId);

    void unlockUser(Long userId);
}
