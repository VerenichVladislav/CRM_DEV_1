package com.example.aviasales2.security;

import com.example.aviasales2.entity.Person;
import com.example.aviasales2.repository.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {


    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person person = this.personRepository.findByUsername(s);
        UserPrincipal userPrincipal = new UserPrincipal(person);

        return userPrincipal;

    }
    public UserPrincipalDetailsService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

}
