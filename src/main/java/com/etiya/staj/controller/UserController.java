package com.etiya.staj.controller;

import com.etiya.staj.business.UserService;
import com.etiya.staj.model.User;
import com.etiya.staj.utility.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/login")
    public DataResult<User> login(@RequestParam(required = true) String username,
                                  @RequestParam(required = true) String password){
        return service.login(username, password);
    }
}