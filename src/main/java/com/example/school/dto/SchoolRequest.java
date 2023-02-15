package com.example.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolRequest {

    private String name;

    private String location;

    private String board;

    private String deptName;

    private String deptHead;

}
