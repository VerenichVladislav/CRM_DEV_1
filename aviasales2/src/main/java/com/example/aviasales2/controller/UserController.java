package com.example.aviasales2.controller;

import com.example.aviasales2.entity.User;
import com.example.aviasales2.service.IWalletService;
import com.example.aviasales2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 10000)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private IWalletService walletService;

    @PostMapping
    public User savePerson(@RequestBody User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setHashPass(bCryptPasswordEncoder.encode(user.getHashPass()));
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        return user;
    }


    @GetMapping("/")
    public List<User> findByLastNameAndEmail(
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email) {
        if(lastName != null && email != null) {
            return userService.findByLastNameAndEmail(lastName, email);
        } else if(lastName != null) {
            return userService.findAllByLastName(lastName);
        } else if (email != null) {
            return Collections.singletonList(userService.findByEmail(email));
        }
        return null;
    }

    @PutMapping
    public void update(@RequestBody User newUser) {
        userService.save(newUser);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        userService.deleteById(id);
    }


}
