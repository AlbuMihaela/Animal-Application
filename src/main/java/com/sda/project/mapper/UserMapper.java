package com.sda.project.mapper;

import com.sda.project.dto.UserDto;
import com.sda.project.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User map(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }
}
