package com.ds.meeting.auth.services;


import com.ds.meeting.auth.model.Users;
import com.ds.meeting.auth.repository.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;
    @Override
    public Integer getCurrentUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(Objects.equals(username, "anonymousUser")){
            return null;
        } else {
            return userDao.findByUsername(username).getId();
        }
    }

    @Override
    public ResponseEntity<?> createUser(Users user) {
        userDao.save(user);
        return ResponseEntity.ok("User Saved");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }
}
