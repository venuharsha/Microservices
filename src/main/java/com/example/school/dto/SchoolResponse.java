package com.example.school.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class SchoolResponse {


    private String id;

    private String name;

    private String location;

    private String board;

    private String deptId;

    private String deptName;

    private String deptHead;
}
