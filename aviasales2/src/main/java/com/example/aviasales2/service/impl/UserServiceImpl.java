package com.example.aviasales2.service.impl;

import com.example.aviasales2.config.filterConfig.UserFilter;
import com.example.aviasales2.entity.QUser;
import com.example.aviasales2.entity.User;
import com.example.aviasales2.repository.UserRepository;
import com.example.aviasales2.service.UserService;
import org.dozer.DozerBeanMapper;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final DozerBeanMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, DozerBeanMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findByUserId(id);
    }


    @Override
    public List <User> findAll(UserFilter userFilter) {
        final QUser qUser = QUser.user;

        BooleanBuilder builder = new BooleanBuilder(qUser.isNotNull());
        if (userFilter.getRole() != null) {
            builder.and(qUser.role.eq(userFilter.getRole()));
        }
        return (List<User>) userRepository.findAll(builder);
    }

    @Override
    public List <User> findAllByLastName(String lastName) {
        return userRepository.findAllByLastName(lastName);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List <User> findByLastNameAndEmail(String lastName, String email) {
        return userRepository.findByLastNameAndEmail(lastName, email);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findByUserId(id);
        if (user.getRole().equals("USER")) {
            userRepository.deleteByUserId(id);
        }
    }

    @Override
    public User findByHashConfirm(String hashConfirm) {
        return userRepository.findByConfirmingHash(hashConfirm);
    }

    @Override
    public void lockUser(Long userId) {
        User user = userRepository.findByUserId(userId);
        if (user.getRole().equals("USER")) {
            user.setLocked(true);
            userRepository.save(user);
        }
    }

    @Override
    public void unlockUser(Long userId) {
        User user = userRepository.findByUserId(userId);
        if (user.getRole().equals("USER")) {
            user.setLocked(false);
            userRepository.save(user);
        }
    }
}
