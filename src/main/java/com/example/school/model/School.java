package com.example.school.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class School {

    @Id
    private String id;

    private String name;

    private String location;

    private String board;

    private String deptName;

    private String deptHead;


}
