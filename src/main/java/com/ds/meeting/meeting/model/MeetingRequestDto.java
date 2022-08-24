package com.ds.meeting.meeting.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeetingRequestDto {
    private String email;
    private String username;
    private String meetingId;
}
