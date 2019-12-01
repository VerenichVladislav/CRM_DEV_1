package com.example.aviasales2.security;



import com.example.aviasales2.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserRepository userRepository;
    private UserPrincipalDetailsService userPrincipalDetailsService;
    public SecurityConfiguration(UserRepository userRepository, UserPrincipalDetailsService userPrincipalDetailsService){
        this.userRepository = userRepository;
        this.userPrincipalDetailsService = userPrincipalDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository))
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers(HttpMethod.POST,"/users").permitAll()
                .antMatchers(HttpMethod.POST,"/trips/*/*/buy").permitAll()
                .antMatchers(HttpMethod.POST, "/trips/city/{city_f_id}/{city_d_id}/transport/{tr_id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/tickets/buyer/{buyer_id}").permitAll()
                .antMatchers(HttpMethod.PUT, "/comments/hotel/*").permitAll()
                .antMatchers(HttpMethod.POST, "/comments").permitAll()
                .antMatchers(HttpMethod.POST).permitAll()
                .antMatchers("/*").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/transports").hasRole("USER")
                .antMatchers("/*/*/*/*/*").permitAll()
                .antMatchers("/tickets/buyer/{buyer_id}").permitAll()
                .antMatchers("/{user_id}/{hotel_id}/{checkIn}/{checkOut}/{roomId}").permitAll()
                .antMatchers("/users").permitAll()
                .antMatchers("/cities").permitAll()
                .antMatchers("/comments").permitAll()
                .antMatchers("/companies").permitAll()
                .antMatchers("/hotels").permitAll()
                .antMatchers("/tours").permitAll()
                .antMatchers("/trips").permitAll()
                .antMatchers("/tickets").permitAll()
                .antMatchers("/international").permitAll()
                .antMatchers("/international/*").permitAll()
                .antMatchers("/users/confirm/*").permitAll()
                .antMatchers("/users/confirm/").permitAll()
                .antMatchers(HttpMethod.GET,"/cities/*").permitAll()
                .antMatchers(HttpMethod.GET,"/users").permitAll()
                .antMatchers(HttpMethod.GET,"/international").permitAll()
                .antMatchers(HttpMethod.GET,"/international/*").permitAll()
                .antMatchers(HttpMethod.GET).permitAll()
                .anyRequest().authenticated();
    }

    @Bean DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
