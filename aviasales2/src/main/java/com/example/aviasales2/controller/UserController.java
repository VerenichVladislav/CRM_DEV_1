package com.example.aviasales2.controller;

import com.example.aviasales2.config.filterConfig.UserFilter;
import com.example.aviasales2.entity.Sender;
import com.example.aviasales2.entity.User;
import com.example.aviasales2.entity.transferObjects.UserDTO;
import com.example.aviasales2.exception.GlobalBadRequestException;
import com.example.aviasales2.service.UserService;
import com.example.aviasales2.util.UserValidator;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    private final Environment environment;

    @Autowired
    public UserController(UserService userService, DozerBeanMapper mapper,
                          UserValidator userValidator, Environment environment) {
        this.userService = userService;
        this.mapper = mapper;
        this.userValidator = userValidator;
        this.environment = environment;
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

        StringBuilder url = new StringBuilder();
        url.append("https://localhost:").append(environment.getProperty("server.port"))
                .append("/users/confirm/").append(user.getConfirmingHash());

        StringBuilder html = new StringBuilder();
        html.append("<html>\n");

        html.append("<body>\n");
        html.append("<h2>Добрый день, ").append(userName).append("!</h2>\n");
        html.append("<p>Это очень важное письмо пришло что бы выподтвердили регистрацию на нашем супер сайте.</p>\n");
        html.append("<p>Нажмите на эту ссылку: ");
        html.append("<a href=\"").append(url).append("\">Aviasales 2.0</a>\n</p>\n");
        html.append("</body>\n");

        html.append("</html>");

        Sender sender = new Sender();
        String subject = "Подтверждение электроннной почты";
        sender.send(subject, html.toString(), user.getEmail());

        userService.save(user);
    }

    @GetMapping("/sendPassword")
    public void sendPassword(@RequestParam(name = "userName") String userName) {
        userService.sendPasswordToEmail(userName);
    }

    @GetMapping("/isLogin")
    public void isLogin() {
    }

    @GetMapping("/isAuthenticated")
    public void isAuthenticated() {
    }

    @GetMapping("/isEmailConfirmed/{userId}")
    public boolean isEmailConfirmed(@PathVariable Long userId) {
        return userService.findById(userId).getState().equals("Confirmed");
    }

    @GetMapping("/")
    public UserDTO findByUserName(@RequestParam String userName) {
        return mapper.map(userService.findByUserName(userName), UserDTO.class);
    }

    @PutMapping
    public void update(@RequestBody UserDTO data, BindingResult result) {
        User user = userService.findByUserName(data.getUserName());
        UserDTO userDto = mapper.map(user, UserDTO.class);
        userDto.setHashPass(data.getHashPass());

        userValidator.updateValidate(userDto, result);
        if (result.hasErrors()) {
            throw new GlobalBadRequestException(result);
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userDto.setHashPass(bCryptPasswordEncoder.encode(data.getHashPass()));

        userService.update(mapper.map(userDto, User.class));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
}
