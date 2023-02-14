package com.example.school.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@Builder
public class DepartmentRequest {

    private String id;
    @NonNull
    private String deptName;

    @NonNull
    private String deptHead;

    private Date createdDate;
}
