package com.example.aviasales2.util;

import com.example.aviasales2.entity.Wallet;
import com.example.aviasales2.entity.transferObjects.UserDTO;
import com.example.aviasales2.entity.transferObjects.WalletDTO;
import com.example.aviasales2.repository.UserRepository;
import com.example.aviasales2.repository.WalletRepository;
import com.example.aviasales2.service.impl.WalletServiceImpl;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public boolean supports(Class <?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO user = (UserDTO) target;
        if (userRepository.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "", "Not unique email");
        }
        if (userRepository.findByUserName(user.getUserName()) != null) {
            errors.rejectValue("userName", "", "Not unique user name");
        }
    }

    public void updateValidate(Object target, Errors errors){
        UserDTO user = (UserDTO) target;
        if(!user.getHashPass().matches("^[A-Za-z0-9]+$")
                || user.getHashPass().length() < 4 || user.getHashPass().length() > 15){
            errors.rejectValue("hashPass", "", "Invalid password!(a-Z 0-9)");
        }
    }
}
