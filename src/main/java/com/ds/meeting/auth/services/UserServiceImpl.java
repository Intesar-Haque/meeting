package com.ds.meeting.auth.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    @Override
    public Integer getCurrentUserId() {
        return 1;
    }
}
