package com.ds.meeting.meeting.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
public class Meeting {
    private Integer id;
    private Integer groupId;
    private Integer createdBy;
    private Date startTime;
    private Date createdAt;
    private String meetingCode;
}
