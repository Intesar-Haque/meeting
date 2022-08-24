package com.ds.meeting.meeting.service;

import com.ds.meeting.auth.services.UserService;
import com.ds.meeting.commons.repository.JdbcFunction;
import com.ds.meeting.meeting.model.Meeting;
import com.ds.meeting.meeting.repository.MeetingDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {
    private final MeetingDao meetingDao;
    private final JdbcFunction jdbcFunction;
    private final UserService userService;
    @Override
    public ResponseEntity<?> scheduleMeeting(String meetingCode, Integer groupId, Date startTime) {
        try{
            meetingDao.save(meetingCode, groupId, Objects.isNull(startTime)? new Date(): startTime);
            return ResponseEntity.ok("Meeting Saved");
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> joinMeeting(String meetingCode, String displayName, String peerId, Integer userId) {
        Map<String, Object> param = new LinkedHashMap<>();
        param.put("meetingCode", meetingCode);
        param.put("displayName", displayName);
        param.put("peerId", peerId);
        param.put("userId", userId);
        Object response = jdbcFunction.getFunctionResult("fn_join_meeting", param);
        if(Objects.nonNull(response)){
            ObjectMapper objectMapper = new ObjectMapper();
            try{
                return new ResponseEntity<>(objectMapper.readValue(response.toString(), Map.class), HttpStatus.OK);
            } catch (Exception e){
                log.error(e.getMessage(), e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> leaveMeeting(String peerId) {
        try{
            meetingDao.deleteByPeerId(peerId);
            return ResponseEntity.ok("Meeting Left");
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
