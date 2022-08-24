package com.ds.meeting.group;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GroupModel {
    private Integer id;
    private String name;
    private String description;
    private Integer createdBy;
    private Integer imageId;
    private Date createdAt;

    public GroupModel() {
    }
    public GroupModel(String name) {
        this.name = name;
    }


}
