package com.example.school.controller;

import com.example.school.dto.SchoolRequest;
import com.example.school.dto.SchoolResponse;
import com.example.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/school/")
public class SchoolController {

    @Autowired
    SchoolService schoolService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createSchoolAndDepartment(@RequestBody SchoolRequest schoolRequest) {
        return schoolService.createSchoolAndDepartment(schoolRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SchoolResponse> getAllSchools() {
        return schoolService.getAllSchools();
    }

    @GetMapping("byId")
    @ResponseStatus(HttpStatus.OK)
    public SchoolResponse getSchoolAndDepartmentById(@RequestParam  String id) {
        return schoolService.getSchoolAndDepartmentById(id);
    }

    @GetMapping("byName")
    @ResponseStatus(HttpStatus.OK)
    public List<SchoolResponse> getSchoolByName(@RequestParam  String name) {
        return schoolService.getSchoolByName(name);
    }
}
