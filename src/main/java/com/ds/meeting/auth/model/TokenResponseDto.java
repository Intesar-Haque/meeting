package com.ds.meeting.auth.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponseDto {
    private String username;
    private Integer id;
    private String fullName;
    private String image;
    private String colorProfile;
    private String token;
}
