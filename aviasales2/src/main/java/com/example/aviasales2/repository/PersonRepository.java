package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    Optional<Person> findById(Integer id);
    Person findByEmail(String email);
    Person findByLastName(String lastName);
    void deleteById(Integer id);

}
