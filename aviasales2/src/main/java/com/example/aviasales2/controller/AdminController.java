package com.example.aviasales2.controller;

import com.example.aviasales2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 10000)
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/isAuthenticated")
    public void isAuthenticated() {
    }

    @GetMapping("/lockUser")
    public void lockUser(@RequestParam Long userId) {
        userService.lockUser(userId);
    }

    @GetMapping("/unlockUser")
    public void unlockUser(@RequestParam Long userId) {
        userService.unlockUser(userId);
    }
}
