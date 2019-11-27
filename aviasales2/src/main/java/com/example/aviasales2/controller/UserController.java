package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Sender;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.entity.User;
import com.example.aviasales2.entity.transferObjects.UserDTO;
import com.example.aviasales2.service.TripService;
import com.example.aviasales2.service.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;

    @Autowired
    private DozerBeanMapper mapper;

    @GetMapping
    public List<UserDTO> findAll (){return userService.findAll().stream()
            .map(entity -> mapper.map(entity, UserDTO.class))
            .collect(Collectors.toList());}

    @PostMapping
    public UserDTO savePerson(@RequestBody @Valid UserDTO userDTO, BindingResult result) {
        if(result.hasErrors())
        {return null;}
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userDTO.setHashPass(bCryptPasswordEncoder.encode(userDTO.getHashPass()));
        return mapper.map(userService.save(mapper.map(userDTO, User.class)), UserDTO.class);
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable(name = "id") Long id) {
        return mapper.map(userService.findById(id),UserDTO.class);
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
    public UserDTO findByUserName(@RequestParam String userName) {
        return mapper.map(userService.findByUserName(userName), UserDTO.class);
    }
    @PutMapping
    public void update(@RequestBody User newUser) {
        userService.save(newUser);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        userService.deleteById(id);
    }

//    @PostMapping("/city/{city_f_id}/{city_d_id}/transport/{tr_id}/saveTrip")
//    public void tripSave(@PathVariable("city_f_id") long cityFromId,
//                         @PathVariable("city_d_id") long cityDestId,
//                         @PathVariable("tr_id") long transportId,
//                         @RequestBody Trip trip) {
//        tripService.save(cityFromId, cityDestId, transportId, trip);
//    }
}
