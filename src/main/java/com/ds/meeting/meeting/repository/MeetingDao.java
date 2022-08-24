package com.ds.meeting.meeting.repository;

import java.util.Date;

public interface MeetingDao {
    void save(String meetingCode, Integer groupId, Date startTime);

    void deleteByPeerId(String peerId);
}
