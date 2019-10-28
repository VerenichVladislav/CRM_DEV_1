package com.example.aviasales2.repository;

import com.example.aviasales2.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findById(Long id);
    User findByEmail(String email);
    void deleteById(Integer id);
    User findUserByFirstName(User user);
    List<User> findByLastNameAndEmail(String lastName, String email);
    List<User> findAllByLastName(String lastName);
}
