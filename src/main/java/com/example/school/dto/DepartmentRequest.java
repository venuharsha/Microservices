package com.example.school.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@Builder
public class DepartmentRequest {

    private String id;

    private String deptName;

    private String deptHead;

    private Date createdDate;
}
