package com.sda.project.mapper;

import com.sda.project.dto.UserDto;
import com.sda.project.dto.UserInfoDto;
import com.sda.project.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserMapper {

    public User mapToUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }

    public UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }
    public User mapToUser(UserInfoDto userInfoDto) {
        User user = new User();
        user.setFirstName(userInfoDto.getFirstName());
        user.setLastName(userInfoDto.getLastName());
        user.setEmail(userInfoDto.getEmail());
        user.setPhoneNumber(userInfoDto.getPhoneNumber());
        return user;
    }

    public UserInfoDto mapToUserInfoDto(User user) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setFirstName(user.getFirstName());
        userInfoDto.setLastName(user.getLastName());
        userInfoDto.setEmail(user.getEmail());
        userInfoDto.setPhoneNumber(user.getPhoneNumber());
        return userInfoDto;
    }

}
