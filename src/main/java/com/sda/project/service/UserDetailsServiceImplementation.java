package com.sda.project.service;

import com.sda.project.model.User;
import com.sda.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User " + email + " does not exist!");
        }
        //daca arunca exceptia iese din metoda... daca trece mai departe sigur am un user in optionalUser
        User user = optionalUser.get();

        Set<GrantedAuthority> userRoles = new HashSet<>();
        userRoles.add(new SimpleGrantedAuthority("ROLE_USER"));
        org.springframework.security.core.userdetails.User userToBeReturned = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), userRoles);
        return userToBeReturned;
    }
}
