package com.example.aviasales2.controller;

import com.example.aviasales2.Constants;
import com.example.aviasales2.config.filterConfig.UserFilter;
import com.example.aviasales2.entity.Sender;
import com.example.aviasales2.entity.User;
import com.example.aviasales2.entity.transferObjects.UserDTO;
import com.example.aviasales2.exception.GlobalBadRequestException;
import com.example.aviasales2.service.UserService;
import com.example.aviasales2.util.UserValidator;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 10000)
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final DozerBeanMapper mapper;
    private final UserValidator userValidator;

    @Autowired
    public UserController(UserService userService, DozerBeanMapper mapper, UserValidator userValidator) {
        this.userService = userService;
        this.mapper = mapper;
        this.userValidator = userValidator;
    }

    @PostMapping("/filter")
    public List <UserDTO> findAll(@RequestBody UserFilter userFilter) {
        return userService.findAll(userFilter).stream()
                .map(entity -> mapper.map(entity, UserDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity <UserDTO> savePerson(@RequestBody @Valid UserDTO userDTO, BindingResult result) {
        userValidator.validate(userDTO, result);
        if (result.hasErrors()) {
            throw new GlobalBadRequestException(result);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userDTO.setHashPass(bCryptPasswordEncoder.encode(userDTO.getHashPass()));
        UserDTO body = mapper.map(userService.save(mapper.map(userDTO, User.class)), UserDTO.class);
        return new ResponseEntity <>(body, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable(name = "id") Long id) {
        return mapper.map(userService.findById(id), UserDTO.class);
    }

    @GetMapping("confirm/{hashConfirm}")
    public void confirmUser(@PathVariable(name = "hashConfirm") String hashConfirm) {
        User user = userService.findByHashConfirm(hashConfirm);
        if (user != null) {
            user.setState("Confirmed");
        }
        userService.save(user);
    }

    @GetMapping("/sendConfirm")
    public void sentConfirmToEmail(@RequestParam(name = "userName") String userName) throws MessagingException {
        User user = userService.findByUserName(userName);
        user.setConfirmingHash(Integer.toString(userName.hashCode()));
        Sender sender = new Sender();
        String subject = "Очень важное сообщение";
        String text = "Добрый день " + userName + "! Это очень важное письмо пришло что бы выподтвердили регистрацию на нашем супер сайте нажмите на эту ссылку" + Constants.URL + "users/confirm/" + user.getConfirmingHash();
        sender.send(subject, text, user.getEmail());

        userService.save(user);
    }

    @GetMapping("/isAuthenticated")
    public void isAuthenticated() {
    }

    @GetMapping("/")
    public UserDTO findByUserName(@RequestParam String userName) {
        return mapper.map(userService.findByUserName(userName), UserDTO.class);
    }

    @PutMapping
    public void update(@RequestBody User newUser) {
        userService.save(newUser);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
}
