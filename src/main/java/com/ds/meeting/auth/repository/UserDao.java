package com.ds.meeting.auth.repository;

import com.ds.meeting.auth.model.Users;
import org.springframework.http.ResponseEntity;

public interface UserDao {

    Users findByUsername(String username);

   void save(Users user);
}
