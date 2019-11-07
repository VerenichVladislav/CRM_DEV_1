package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Sender;
import com.example.aviasales2.entity.Sender;
import com.example.aviasales2.entity.User;
import com.example.aviasales2.service.IWalletService;
import com.example.aviasales2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import javax.mail.MessagingException;
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

    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

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
    @GetMapping("confirm/{hashConfirm}")
    public void confirmUser(@PathVariable(name = "hashConfirm") String hashConfirm){
        User user = userService.findByHashConfirm(hashConfirm);
        if(user != null){
            user.setState("Confirmed");
        }
        userService.save(user);
    }
    @GetMapping("/sendConfirm")
    public void sentConfirmToEmail(@RequestParam(name ="userName")String userName) throws MessagingException {
        User user = userService.findByUserName(userName);
        user.setConfirmingHash(Integer.toString(userName.hashCode()));
        Sender sender = new Sender();
        String subject = "Очень важное сообщение";
        String text = "Добрый день \"+ userName +\"Это очень важное письмо пришло что бы выподтвердили регистрацию на нашем супер сайте нажмите на эту ссылку http://localhost:8080/users/confirm/"+user.getConfirmingHash();
        sender.send(subject,text,user.getEmail());

        userService.save(user);
    }


    @GetMapping("/")
    public User findByUserName(@RequestParam String userName) {
        return userService.findByUserName(userName);
    }

//    @GetMapping("/")
//    public List<User> findByLastNameAndEmail(
//            @RequestParam(required = false) String lastName,
//            @RequestParam(required = false) String email) {
//        if(lastName != null && email != null) {
//            return userService.findByLastNameAndEmail(lastName, email);
//        } else if(lastName != null) {
//            return userService.findAllByLastName(lastName);
//        } else if (email != null) {
//            return Collections.singletonList(userService.findByEmail(email));
//        }
//        return null;
//    }

    @PutMapping
    public void update(@RequestBody User newUser) {
        userService.save(newUser);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        userService.deleteById(id);
    }


}
