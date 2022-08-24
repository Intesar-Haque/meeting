package com.ds.meeting.group;

import com.ds.meeting.commons.dto.PageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @CrossOrigin("*")
    @PostMapping("/list")
    public ResponseEntity<?> list(@RequestBody PageRequestDto request) {
        return groupService.groupList(request);
    }
}
