package com.etiya.staj.utility;

import com.etiya.staj.model.User;
import com.etiya.staj.model.UserDto;

import java.util.ArrayList;
import java.util.List;

public class DtoMapUtility {
    public static List<UserDto> convertToDto(List<User> users){
        List<UserDto> dtos = new ArrayList<>();
        for (int i = 0; i < users.size(); i++){
            String name = users.get(i).getName();
            String username = users.get(i).getUsername();
            String email = users.get(i).getEmail();
            dtos.add(new UserDto(name,username,email));
        }
        return dtos;
    }
}
