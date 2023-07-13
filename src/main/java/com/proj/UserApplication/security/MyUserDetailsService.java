package com.proj.UserApplication.security;

import com.proj.UserApplication.model.User;
import com.proj.UserApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(userEmail);

        user.orElseThrow(() -> new UsernameNotFoundException("Not Found:" + userEmail));

        return user.map(MyUserDetails::new).get();
    }
}
