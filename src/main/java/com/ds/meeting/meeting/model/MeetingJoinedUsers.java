package com.ds.meeting.meeting.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class MeetingJoinedUsers {
    private Integer groupId;
    private Integer createdBy;
    private Date startTime;
    private Date createdAt;
    private String meetingCode;
}
