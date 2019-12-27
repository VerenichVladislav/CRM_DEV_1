package com.example.aviasales2.security;


import com.example.aviasales2.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserRepository userRepository;
    private UserPrincipalDetailsService userPrincipalDetailsService;

    public SecurityConfiguration(UserRepository userRepository, UserPrincipalDetailsService userPrincipalDetailsService) {
        this.userRepository = userRepository;
        this.userPrincipalDetailsService = userPrincipalDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), this.userRepository))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository))
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.POST, "/hotels").permitAll()
                .antMatchers(HttpMethod.POST, "/hotels/filter").permitAll()
                .antMatchers(HttpMethod.POST, "/hotels/filter/*").permitAll()
                .antMatchers(HttpMethod.POST, "/trips").permitAll()
                .antMatchers(HttpMethod.POST, "/trips/dto").permitAll()
                .antMatchers(HttpMethod.POST, "/trips/*").permitAll()
                .antMatchers(HttpMethod.POST, "/trips/*/*/buy").permitAll()
                .antMatchers(HttpMethod.POST, "/trips/city/{city_f_id}/{city_d_id}/transport/{tr_id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/transports").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/transports/filter").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/companies/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/comments/*").permitAll()
                .antMatchers(HttpMethod.POST, "/tickets/buyer/{buyer_id}").permitAll()
                .antMatchers(HttpMethod.PUT, "/comments/hotel/*").permitAll()
                .antMatchers(HttpMethod.POST, "/comments").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST).permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/transports").permitAll()
                .antMatchers("/tickets/buyer/{buyer_id}").permitAll()
                .antMatchers("/cities").permitAll()
                .antMatchers("/comments").permitAll()
                .antMatchers("/companies").permitAll()
                .antMatchers("/hotels").permitAll()
                .antMatchers(HttpMethod.POST, "/hotels/{user_id}/{hotel_id}/{checkIn}/{checkOut}/{roomId}").hasAnyRole("USER", "ADMIN")
                .antMatchers("/tours").permitAll()
                .antMatchers("/trips").permitAll()
                .antMatchers("/tickets").permitAll()
                .antMatchers("/international").permitAll()
                .antMatchers("/international/*").permitAll()
                .antMatchers("/users/confirm/*").permitAll()
                .antMatchers("/users/confirm/").permitAll()

                .antMatchers(HttpMethod.GET, "/users/sendPassword/*").permitAll()
                .antMatchers(HttpMethod.GET, "/sendConfirm/*").permitAll()
                .antMatchers(HttpMethod.GET, "/cities/name/*").permitAll()
                .antMatchers(HttpMethod.GET, "/transports/name/*").permitAll()
                .antMatchers(HttpMethod.GET, "/cities/*").permitAll()
                .antMatchers(HttpMethod.GET, "/cities").permitAll()
                .antMatchers(HttpMethod.GET,"/hotels/{hotelId}").permitAll()
                .antMatchers(HttpMethod.GET, "/transports/*").permitAll()
                .antMatchers(HttpMethod.GET, "/transports").permitAll()
                .antMatchers(HttpMethod.GET, "/comments/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/wallets/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/wallets/*/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/wallets/*/*/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/wallets/{userId}/confirm/{hashConfirm}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/wallets/{userId}/confirm/{hashConfirm}/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/comments/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/comments").permitAll()
                .antMatchers(HttpMethod.GET, "/socket").permitAll()
                .antMatchers(HttpMethod.GET, "/comments/*/*").hasAnyRole("USER", "ADMIN")


                .antMatchers(HttpMethod.GET, "/reservations/*").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET,"/reservations/rooms/*").permitAll()
                .antMatchers(HttpMethod.GET, "/trips/*").permitAll()
                .antMatchers(HttpMethod.GET, "/users").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/international").permitAll()
                .antMatchers(HttpMethod.GET, "/international/*").permitAll()
                .antMatchers(HttpMethod.GET, "/admin/lockUser").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/admin/lockUser/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/admin/unlockUser").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/admin/unlockUser/*").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/users/isLogin").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/users/isAuthenticated").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/admin/isAuthenticated").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/users/isEmailConfirmed/{userId}").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
