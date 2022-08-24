package com.ds.meeting.auth.repository;

import com.ds.meeting.auth.model.Users;
import com.ds.meeting.auth.model.UsersRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao{
    private final JdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public Users findByUsername(String username) {
        final String sql = "SELECT * FROM users WHERE username= ? LIMIT 1";
        Users user = null;
        try{
            user = jdbcTemplate.queryForObject(sql, new UsersRowMapper(), username);
        } catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return Objects.isNull(user)? new Users():user;
    }

    @Override
    public void save(Users user) {
        final String sql ="INSERT INTO `users` ( `username`, `name`, `password`, `designation`, `email`) " +
                "VALUES ( ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(),
                user.getName(), passwordEncoder.encode(user.getPassword()),
                user.getDesignation(), user.getEmail() );
    }
}
