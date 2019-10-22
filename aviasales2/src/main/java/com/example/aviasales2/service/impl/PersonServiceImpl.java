package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Person;
import com.example.aviasales2.repository.PersonRepository;
import com.example.aviasales2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Optional<Person> findById(Integer id) {
        return personRepository.findById(id);
    }

    @Override
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> findAllByLastName(String lastName) {
        return personRepository.findAllByLastName(lastName);
    }

    @Override
    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    @Override
    public void update(Person person) {
        personRepository.save(person);
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }

    @Override
    public void deleteById(Integer id) {
        personRepository.deleteById(id);
    }
}
