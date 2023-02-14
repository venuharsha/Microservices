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

    @NonNull
    private String deptName;

    @NonNull
    private String deptHead;
}
