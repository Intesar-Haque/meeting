package com.ds.meeting.group;


import com.ds.meeting.commons.dto.PageRequestDto;
import com.ds.meeting.commons.repository.JdbcFunction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final JdbcFunction jdbcFunction;
    @Override
    public ResponseEntity<?> groupList(PageRequestDto request) {
        List<GroupModel> resultList = new ArrayList<>();
        resultList.add(new GroupModel("Computer Lab 101"));
        resultList.add(new GroupModel("Computer Lab 102"));
        resultList.add(new GroupModel("Computer Lab 103"));
        return new ResponseEntity<>(new PageImpl<>(resultList, PageRequest.of(request.getPage(),request.getSize()), 3), HttpStatus.OK);
    }
}
