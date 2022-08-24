package com.ds.meeting.commons.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PageRequestDto {
    private Integer page;
    private Integer  size;
    private Object filter;
    private String search;
    private String order;
    private String orderBy;
}
