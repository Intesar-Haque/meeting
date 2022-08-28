package com.ds.meeting.auth.controller;

import com.ds.meeting.auth.model.TokenResponseDto;
import com.ds.meeting.auth.model.Users;
import com.ds.meeting.auth.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {

//    private final AuthenticationManager authenticationManager;
//    private final UserService userService;
//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody Users user){
//        return userService.createUser(user);
//    }
//    @PostMapping("/authenticate")
//    public ResponseEntity<?> authenticate(@RequestParam String username, @RequestParam String password){
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        TokenResponseDto responseDto = new TokenResponseDto();
//        responseDto.setColorProfile("Default");
//        Users user = (Users) authentication.getPrincipal();
//        responseDto.setFullName(user.getName());
//        responseDto.setId(user.getId());
//        responseDto.setUsername(user.getUsername());
//        responseDto.setToken(userService.authenticate(username, password));
//        return new ResponseEntity<>(responseDto, HttpStatus.OK);
//    }


    private final List<Users> USERS = new ArrayList<>();
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user){
        USERS.add(user);
        return new ResponseEntity<>("User created", HttpStatus.OK);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestParam String username, @RequestParam String password){
        int idx = -1;
        for(int i=0; i < USERS.size(); i++){
            if(Objects.nonNull(USERS.get(i))
                    && Objects.equals(USERS.get(i).getUsername(), username)
                    && Objects.equals(USERS.get(i).getPassword(), password)){
                idx=i;
                break;
            }
        }
        if(idx>-1){
            TokenResponseDto responseDto = new TokenResponseDto();
            responseDto.setColorProfile("Default");
            Users user = USERS.get(idx);
            responseDto.setFullName(user.getName());
            responseDto.setId(idx+1);
            responseDto.setUsername(user.getUsername());
            responseDto.setToken(UUID.randomUUID().toString());
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            throw new UsernameNotFoundException("Invalid Credentials");
        }
    }
}
