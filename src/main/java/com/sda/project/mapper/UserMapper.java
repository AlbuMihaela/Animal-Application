package com.sda.project.mapper;

import com.sda.project.dto.UserDto;
import com.sda.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User map(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

        String passwordInPlainText = userDto.getPassword();
        String passwordInCryptoText = bCryptPasswordEncoder.encode(passwordInPlainText);
        user.setPassword(passwordInCryptoText);

        return user;
    }


}
