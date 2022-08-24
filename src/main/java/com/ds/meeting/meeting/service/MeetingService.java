package com.ds.meeting.meeting.service;

import org.springframework.http.ResponseEntity;

import java.util.Date;

public interface MeetingService {
    ResponseEntity<?> scheduleMeeting(String meetingCode, Integer groupId, Date startTime);
    ResponseEntity<?> joinMeeting(String meetingCode, String displayName, String peerId, Integer userId);

    ResponseEntity<?> leaveMeeting(String peerId);
}
