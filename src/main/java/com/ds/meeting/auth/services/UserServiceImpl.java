package com.ds.meeting.auth.services;


import com.ds.meeting.auth.model.Users;
import com.ds.meeting.auth.repository.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import static io.jsonwebtoken.Jwts.builder;
import static io.jsonwebtoken.SignatureAlgorithm.HS512;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

//    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;

    private final String secret="secret";
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
    public String authenticate(String username, String password) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        final UserDetails userDetails = loadUserByUsername(username);
//        final String token = generateToken(userDetails);
        return generateToken(userDetails);

    }

    private String generateToken(UserDetails userDetails) {
        final Date createdDate = new Date();

        return builder().setClaims(new HashMap<>()).setSubject(userDetails.getUsername()).setIssuedAt(createdDate)
                .setExpiration(new Date(createdDate.getTime() + + 6400000)).signWith(HS512, secret).compact();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }
}
