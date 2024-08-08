package com.etiya.staj.controller;

import com.etiya.staj.business.UserService;
import com.etiya.staj.model.User;
import com.etiya.staj.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping()
    public List<UserDto> getAll(){
        return service.getAllUsers();
    }

    @GetMapping("/usernames")
    public List<String> getFromWebUrl(){
        return service.getAllUsernames();
    }

    @GetMapping("/{username}")
    public UserDto get(@PathVariable String username){
        return service.getUser(username);
    }

    @PostMapping()
    public UserDto add(@RequestBody UserDto userDto){
        return service.addUser(userDto);
    }

    @DeleteMapping("/{username}")
    public String delete(@PathVariable String username){
        return service.deleteUser(username);
    }
}
