package com.example.aviasales2.repository;

import com.example.aviasales2.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findById(Integer id);
    User findByEmail(String email);
    void deleteById(Integer id);
    List<User> findAllByLastName(String lastName);
}
