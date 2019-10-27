package com.example.aviasales2.security;

import com.auth0.jwt.JWT;
import com.example.aviasales2.entity.Person;
import com.example.aviasales2.repository.PersonRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private PersonRepository personRepository;

    public  JwtAuthorizationFilter(AuthenticationManager authenticationManager,PersonRepository personRepository){
        super(authenticationManager);
        this.personRepository = personRepository;
    }
    @Override
    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response , FilterChain chain) throws IOException, ServletException{
        String header = request.getHeader(JwtProterties.HEADER_STRING);

        if (header == null || !header.startsWith(JwtProterties.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }

    }
    private  Authentication getUsernamePasswordAuthentication(HttpServletRequest request){
        String token = request.getHeader(JwtProterties.HEADER_STRING)
                .replace(JwtProterties.TOKEN_PREFIX,"");
        if (token != null){
            String userName = JWT.require(HMAC512(JwtProterties.SECRET.getBytes()))
                    .build()
                    .verify(token)
                    .getSubject();
            if(userName != null){
                Person person = personRepository.findByUsername(userName);
                UserPrincipal principal = new UserPrincipal(person);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName,null,principal.getAuthorities());
                return auth;
            }
            return null;
        }
        return null;



    }

}
