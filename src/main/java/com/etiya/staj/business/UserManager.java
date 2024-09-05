package com.etiya.staj.business;

import com.etiya.staj.dataAccess.UserRepository;
import com.etiya.staj.model.User;
import com.etiya.staj.utility.results.DataResult;
import com.etiya.staj.utility.results.ErrorDataResult;
import com.etiya.staj.utility.results.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService{
    @Autowired
    private UserRepository repository;
    @Override
    public DataResult<User> login(String username, String password) {
        if(repository.existsByUsername(username)){
            User user = repository.findByUsername(username);
            if(user.getPassword().equals(password)){
                return new SuccessDataResult<>("Logged In", user);
            }else{
                return new ErrorDataResult<>("Password is wrong!");
            }
        }
        return new ErrorDataResult<>("User is invalid!");
    }
}
