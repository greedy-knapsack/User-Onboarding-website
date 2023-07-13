package com.proj.UserApplication.security;

import com.proj.UserApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    public UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/api/admin/users").hasRole("ADMIN")
                .antMatchers("/api/users").hasAnyRole("USER","ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin()
                .usernameParameter("email")
                .successHandler((request, response, authentication) -> {
                    String userEmail = authentication.getName();
                    String userRole = userDetailsService.loadUserByUsername(userEmail).getAuthorities().iterator().next()
                            .getAuthority();
                    if (userRole.equals("ROLE_ADMIN")) {
                        response.sendRedirect("/api/admin/users");
                    } else if (userRole.equals("ROLE_USER")) {
                        response.sendRedirect("/api/users");
                    } else {
                        // Handle other roles or scenarios
                        response.sendRedirect("/");
                    }
                })
                .and()
                .logout().logoutSuccessUrl("/").permitAll();
    }
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
