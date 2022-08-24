package com.ds.meeting.group;

import com.ds.meeting.commons.dto.PageRequestDto;
import org.springframework.http.ResponseEntity;

public interface GroupService {
    ResponseEntity<?> groupList(PageRequestDto request);
}
