package com.AppUser.service;
import com.AppUser.entity.AppUser;
import com.AppUser.payload.LoginDto;
import com.AppUser.payload.UserDto;
import com.AppUser.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    public AppUserRepository appUserRepository;
    public UserDto addUser(UserDto dto){
        AppUser user=mapToEntity(dto);
        AppUser savedUser=appUserRepository.save(user);
        return mapToDto(savedUser);
    }
    public UserDto mapToDto(AppUser user){
        UserDto dto=new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setEmailId(user.getEmailId());
        dto.setPassword(user.getPassword());
        return dto;
    }
    public AppUser mapToEntity(UserDto dto){
        AppUser user=new AppUser();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmailId(dto.getEmailId());
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        return user;
    }
    public boolean verifyLogin(LoginDto loginDto){
       Optional<AppUser>opUser=appUserRepository.findByUsername(loginDto.getUsername());
       if(opUser.isPresent()){
           AppUser user=opUser.get();
           return BCrypt.checkpw(loginDto.getPassword(),user.getPassword());
       }
       return false;
    }
}
