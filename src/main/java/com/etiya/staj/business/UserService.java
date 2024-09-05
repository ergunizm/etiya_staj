package com.etiya.staj.business;

import com.etiya.staj.model.User;
import com.etiya.staj.utility.results.DataResult;

public interface UserService {
    DataResult<User> login(String username, String password);
}
