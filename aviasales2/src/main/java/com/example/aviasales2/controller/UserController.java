package com.example.aviasales2.controller;

import com.example.aviasales2.entity.User;
import com.example.aviasales2.entity.Wallet;
import com.example.aviasales2.service.IWalletService;
import com.example.aviasales2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.WatchService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private IWalletService walletService;

    @PostMapping("/save")
    public User savePerson(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/getById/{id}")
    private User findById(@PathVariable(name = "id") Long id) {

        User user = userService.findById(id);
        return user;
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
