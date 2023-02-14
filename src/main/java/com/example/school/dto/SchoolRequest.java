package com.example.school.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class SchoolRequest {

    @NonNull
    private String name;

    @NonNull
    private String location;

    @NonNull
    private String board;

    private String deptName;

    private String deptHead;

}
