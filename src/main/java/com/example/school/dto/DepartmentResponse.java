package com.example.school.dto;

import jakarta.persistence.Id;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {

    @Id
    private String deptId;

    private String deptName;

    private String deptHead;
}
