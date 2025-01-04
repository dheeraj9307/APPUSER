package com.AppUser.service;

import com.AppUser.payload.LoginDto;
import com.AppUser.payload.UserDto;

public interface AppUserService {
    public UserDto addUser(UserDto dto);
    public boolean verifyLogin(LoginDto loginDto);
}
