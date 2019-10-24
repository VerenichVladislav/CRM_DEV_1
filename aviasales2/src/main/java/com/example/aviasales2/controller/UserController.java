package com.example.aviasales2.controller;

import com.example.aviasales2.entity.User;
import com.example.aviasales2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public User savePerson(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/getById")
    private Optional<User> findById(@RequestParam Integer id) {
        return userService.findById(id);
    }

    @GetMapping("/getByEmail")
    private User findByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/getAllByLastName")
    private List<User> findByLastName(@RequestParam String lastName) {
        return userService.findAllByLastName(lastName);
    }

    @PostMapping("/update")
    private void update(@RequestBody User newUser) {
        userService.save(newUser);
    }

    @GetMapping("/deleteById")
    private void deleteById(@RequestParam Integer id) {
        userService.deleteById(id);
    }
}
