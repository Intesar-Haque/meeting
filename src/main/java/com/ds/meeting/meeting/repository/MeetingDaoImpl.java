package com.ds.meeting.meeting.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class MeetingDaoImpl implements MeetingDao{
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate template;

    @Override
    public void save(String meetingCode, Integer groupId, Date startTime) {
        final String sql ="INSERT INTO `meeting` ( `meeting_code`, `created_by`, `group_id`, `start_time`) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, meetingCode, 1, groupId, startTime);
    }

    @Override
    public void deleteByPeerId(String peerId) {
        final String sql ="DELETE FROM `meeting_joined_users` where peer_id= ?";
        jdbcTemplate.update(sql, peerId);
    }
}
