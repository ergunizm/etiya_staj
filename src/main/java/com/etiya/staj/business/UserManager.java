package com.etiya.staj.business;

import com.etiya.staj.model.User;

import com.etiya.staj.model.UserDto;
import com.etiya.staj.utility.DtoMapUtility;
import com.etiya.staj.utility.JsonUtility;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserManager implements UserService{
    private List<UserDto> dtos;

    public UserManager() throws IOException {
        List<User> users = JsonUtility.readJsonFromUrl("https://jsonplaceholder.typicode.com/users");
        dtos = DtoMapUtility.convertToDto(users);
    }
    public List<UserDto> getAllUsers(){
        return dtos;
    }

    @Override
    public UserDto getUser(String username) {
        for (int i = 0; i < dtos.size(); i++){
            if(dtos.get(i) != null && dtos.get(i).getUsername().toLowerCase().equals(username.toLowerCase())){
                return dtos.get(i);
            }
        }
        return null;
    }

    @Override
    public UserDto addUser(UserDto user) {
        dtos.add(user);

        return user;
    }

    @Override
    public String deleteUser(String username) {
        UserDto del = getUser(username);
        dtos.remove(del);

        return "Successfully deleted person "+ username;
    }

    @Override
    public List<String> getAllUsernames() {
        List<String> unames = new ArrayList<>();

        for (int i = 0; i < dtos.size(); i++) {
            unames.add(dtos.get(i).getUsername());
        }

        return unames;
    }
}