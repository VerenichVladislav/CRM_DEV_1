package com.example.aviasales2.util;

import com.example.aviasales2.entity.transferObjects.UserDTO;
import com.example.aviasales2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class UserValidator implements Validator {
    @Autowired
    private UserRepository userRepository;
    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO user = (UserDTO) target;
        if(userRepository.findByEmail(user.getEmail()) != null){
            errors.rejectValue("email", "", "Not unique email");
        }
        if(userRepository.findByUserName(user.getUserName()) != null){
            errors.rejectValue("userName", "", "Not unique user name");
        }

    }
}
