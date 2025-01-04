package com.AppUser.controller;
import com.AppUser.payload.LoginDto;
import com.AppUser.payload.UserDto;
import com.AppUser.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  @Autowired
   public AppUserService appUserService;
   @PostMapping("/addUser")
    public ResponseEntity<String>addUser(@RequestBody UserDto dto) {
//       System.out.println(dto.getId());
//       System.out.println(dto.getName());
       UserDto savedUser=appUserService.addUser(dto);
       return new ResponseEntity<>("done", HttpStatus.CREATED);
   }
  @PostMapping("/login")
   public ResponseEntity<String>login(@RequestBody LoginDto loginDto){
       boolean status=appUserService.verifyLogin(loginDto);
       if(status){
           return new ResponseEntity<>("UserSigned in",HttpStatus.OK);
       }
       return new ResponseEntity<>("Invalid Credentials",HttpStatus.UNAUTHORIZED);
   }
}
