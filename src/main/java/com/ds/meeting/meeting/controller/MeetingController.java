package com.ds.meeting.meeting.controller;

import com.ds.meeting.meeting.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin("*")
@RequestMapping("meeting")
@RequiredArgsConstructor
public class MeetingController {
    private final MeetingService meetingService;

    @PostMapping("/create")
    public ResponseEntity<?> scheduleMeeting(
            @RequestParam String meetingCode,
            @RequestParam(required = false) Integer groupId,
            @RequestParam(required = false) Date scheduleDate
    ) { return meetingService.scheduleMeeting(meetingCode, groupId, scheduleDate); }

    @PostMapping("/join")
    public ResponseEntity<?> joinMeeting( @RequestParam String meetingCode, @RequestParam String peerId, @RequestParam(required = false) String displayName, @RequestParam(required = false) Integer userId) {
        return meetingService.joinMeeting(meetingCode, displayName, peerId, userId);
    }

    @PostMapping("/leave")
    public ResponseEntity<?> leaveMeeting( @RequestParam String peerId) {
        return meetingService.leaveMeeting(peerId);
    }
}
