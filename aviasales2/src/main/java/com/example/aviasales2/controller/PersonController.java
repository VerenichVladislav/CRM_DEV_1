package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Person;
import com.example.aviasales2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/save")
    public Person savePerson(@RequestBody Person person) {
        return personService.save(person);
    }

    @GetMapping("/getById")
    private Optional<Person> findById(@RequestParam Integer id) {
        return personService.findById(id);
    }

    @GetMapping("/getByEmail")
    private Person findByEmail(@RequestParam String email) {
        return personService.findByEmail(email);
    }

    @GetMapping("/getAllByLastName")
    private List<Person> findByLastName(@RequestParam String lastName) {
        return personService.findAllByLastName(lastName);
    }

    @PostMapping("/update")
    private void update(@RequestBody Person newPerson) {
        personService.save(newPerson);
    }

    @GetMapping("/deleteById")
    private void deleteById(@RequestParam Integer id) {
        personService.deleteById(id);
    }
}
