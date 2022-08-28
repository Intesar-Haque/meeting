package com.ds.meeting.meeting.controller;

import com.ds.meeting.meeting.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("meeting")
@RequiredArgsConstructor
public class MeetingController {
//    private final MeetingService meetingService;
//
//    @PostMapping("/create")
//    public ResponseEntity<?> scheduleMeeting(
//            @RequestParam String meetingCode,
//            @RequestParam(required = false) Integer groupId,
//            @RequestParam(required = false) Date scheduleDate
//    ) { return meetingService.scheduleMeeting(meetingCode, groupId, scheduleDate); }
//
//    @PostMapping("/join")
//    public ResponseEntity<?> joinMeeting( @RequestParam String meetingCode, @RequestParam String peerId, @RequestParam(required = false) String displayName, @RequestParam(required = false) Integer userId) {
//        return meetingService.joinMeeting(meetingCode, displayName, peerId, userId);
//    }
//
//    @PostMapping("/leave")
//    public ResponseEntity<?> leaveMeeting( @RequestParam String peerId) {
//        return meetingService.leaveMeeting(peerId);
//    }


    private final HashMap<String, List<Map<String, Object>>> MEETINGS = new HashMap<>();
    @PostMapping("/create")
    public ResponseEntity<?> scheduleMeeting(
            @RequestParam String meetingCode,
            @RequestParam(required = false) Integer groupId,
            @RequestParam(required = false) Date scheduleDate
    ) {
        MEETINGS.put(meetingCode, new ArrayList<>());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinMeeting( @RequestParam String meetingCode, @RequestParam String peerId, @RequestParam(required = false) String displayName, @RequestParam(required = false) Integer userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("peerId", peerId);
        map.put("name", displayName);
        MEETINGS.get(meetingCode).add(map);
        Map<String, Object> response = new HashMap<>();
        response.put("isMeetingCreator", Objects.equals(userId, 1));
        response.put("joinedUsers", MEETINGS.get(meetingCode));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/leave")
    public ResponseEntity<?> leaveMeeting( @RequestParam String peerId, @RequestParam String meetingCode) {
        int idx = -1;
        for(int i=0; i < MEETINGS.get(meetingCode).size(); i++){
            if(peerId == MEETINGS.get(meetingCode).get(i).get("peerId")){
                idx=i;
                break;
            }
        }
        if(idx >-1){
            MEETINGS.get(meetingCode).remove(idx);
        }
        return new ResponseEntity<>("Meeting Left",HttpStatus.OK);
    }
    @PostMapping("/close")
    public ResponseEntity<?> closeMeeting(@RequestParam String meetingCode) {
        MEETINGS.remove(meetingCode);
        return new ResponseEntity<>("Meeting Closed",HttpStatus.OK);
    }
}
