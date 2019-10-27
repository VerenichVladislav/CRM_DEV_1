package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    Optional<Person> findById(Integer id);
    Person findByEmail(String email);
    Person findByUsername(String username);
    void deleteById(Integer id);
    List<Person> findAllByLastName(String lastName);
}
