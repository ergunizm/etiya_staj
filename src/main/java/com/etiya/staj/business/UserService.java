package com.etiya.staj.business;

import com.etiya.staj.model.User;
import com.etiya.staj.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUser(String username);
    UserDto addUser(UserDto user);
    String deleteUser(String username);
    List<String> getAllUsernames();
}
