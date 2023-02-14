package com.example.school.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class SchoolResponse {


    private String id;

    @NonNull
    private String name;

    @NonNull
    private String location;

    @NonNull
    private String board;

    private String deptName;

    private String deptHead;
}
