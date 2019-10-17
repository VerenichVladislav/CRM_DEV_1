package com.example.aviasales2.service;

import com.example.aviasales2.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person save(Person person);
    Optional<Person> findById(Integer id);
    Iterable<Person> findAll();
    List<Person> findAllByLastName(String lastName);
    Person findByEmail(String email);
    void update(Person person);
    void delete(Person person);
    void deleteById(Integer id);
}
