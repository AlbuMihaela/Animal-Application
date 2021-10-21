package com.sda.project.service;

import com.sda.project.dto.UserDto;
import com.sda.project.mapper.UserMapper;
import com.sda.project.model.User;
import com.sda.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    public void addUser(UserDto userDto) {
        User user = userMapper.map(userDto);
        userRepository.save(user);


    }
}
