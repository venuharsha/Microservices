package com.example.school.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@Builder
public class DepartmentRequest {

    private String deptId;

    private String deptName;

    private String deptHead;

}
