package com.ds.meeting.auth.services;

import com.ds.meeting.auth.model.Users;
import org.springframework.http.ResponseEntity;

public interface UserService {
    Integer getCurrentUserId();

    ResponseEntity<?> createUser(Users user);

    String authenticate(String username, String password);
}
