package com.ds.meeting.commons.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.OutputKeys;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class TestController {

    @PostMapping("/test")
    public ResponseEntity<?> test(){return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
