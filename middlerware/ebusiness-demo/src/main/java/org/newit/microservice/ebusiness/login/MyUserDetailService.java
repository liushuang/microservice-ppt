package org.newit.microservice.ebusiness.login;

import java.util.ArrayList;

import org.newit.microservice.ebusiness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        org.newit.microservice.ebusiness.model.User userFromDB = userRepository.getUserByName(name);
        if (userFromDB != null) {
            return new User(name, userFromDB.getPassword(), new ArrayList<>());
        }
        return null;
    }
}
