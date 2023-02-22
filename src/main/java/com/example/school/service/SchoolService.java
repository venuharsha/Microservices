package com.example.school.service;

import com.example.school.dto.DepartmentRequest;
import com.example.school.dto.DepartmentResponse;
import com.example.school.dto.SchoolRequest;
import com.example.school.dto.SchoolResponse;
import com.example.school.exception.SchoolNotFoundException;
import com.example.school.model.School;
import com.example.school.repository.SchoolRepository;
import com.example.school.util.RandomGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

    private static final String DEPARTMENT_REST_ENDPOINT = "http://department-service/api/department/";

    @Autowired
    SchoolRepository schoolrepository;

    @Autowired
    RestTemplate restTemplate;

    public String createSchool(SchoolRequest schoolRequest) {


        School s = School.builder()
                .id(RandomGenerator.randomUUID())
                .name(schoolRequest.getName())
                .location(schoolRequest.getLocation())
                .board(schoolRequest.getBoard())
                .build();
        schoolrepository.save(s);
        return "School created successfully";
    }

    public String createSchoolAndDepartment(SchoolRequest schoolRequest) {

        String id = RandomGenerator.randomUUID();
        School s2 = School.builder()
                .id(id)
                .name(schoolRequest.getName())
                .location(schoolRequest.getLocation())
                .board(schoolRequest.getBoard())
                .deptName(schoolRequest.getDeptName())
                .deptHead(schoolRequest.getDeptHead())
                .build();
        schoolrepository.save(s2);

        DepartmentRequest departmentRequest = DepartmentRequest.builder()
                .deptId(id)
                .deptName(schoolRequest.getDeptName())
                .deptHead(schoolRequest.getDeptHead())
                .build();

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(DEPARTMENT_REST_ENDPOINT + "create", departmentRequest, String.class);
        String message = responseEntity.getBody();

        if (StringUtils.isNotBlank(message) && message.equals("Department created successfully")) {
            return "Both school and department created successfully";
        } else
            return "School and department insertion issue";
    }

    public List<SchoolResponse> getAllSchools() {

        List<School> schoolList = schoolrepository.findAll();
        return schoolList.stream().map(school -> mapSchoolResponse(school)).toList();
    }

    private SchoolResponse mapSchoolResponse(School school) {
        return SchoolResponse.builder()
                .id(school.getId())
                .name(school.getName())
                .location(school.getLocation())
                .board(school.getBoard())
                .build();
    }

    public SchoolResponse getSchoolById(String id) {
        Optional<School> byId = schoolrepository.findById(id);

        if (byId.isPresent()) {
            return mapSchoolResponse(byId.get());
        } else throw new SchoolNotFoundException("No school is found with given id: " + id);
    }

    public SchoolResponse getSchoolAndDepartmentById(String id) {
        Optional<School> optionalSchool = schoolrepository.findById(id);

        if (optionalSchool.isPresent()) {
            School s3 = optionalSchool.get();

            ResponseEntity<DepartmentResponse> responseEntity = restTemplate.getForEntity(DEPARTMENT_REST_ENDPOINT + "byId?deptId=" + id, DepartmentResponse.class);
            DepartmentResponse departmentResponse = responseEntity.getBody();

            assert departmentResponse != null;
            return mapSchoolAndDepartmentResponse(s3, departmentResponse);
        } else throw new SchoolNotFoundException("No school and department are found with given id: " + id);
    }

    private SchoolResponse mapSchoolAndDepartmentResponse(School s3, DepartmentResponse departmentResponse) {
        return SchoolResponse.builder()
                .id(s3.getId())
                .name(s3.getName())
                .location(s3.getLocation())
                .board(s3.getBoard())
                .deptId(departmentResponse.getDeptId())
                .deptName(departmentResponse.getDeptName())
                .deptHead(departmentResponse.getDeptHead())
                .build();
    }

    public List<SchoolResponse> getSchoolByName(String name) {
        List<School> byName = schoolrepository.findByName(name);

        if (!CollectionUtils.isEmpty(byName)) {
            return byName.stream().map(school -> mapSchoolResponse(school)).toList();
        } else throw new SchoolNotFoundException("School not found for given name: " + name);
    }

//    public List<SchoolResponse> getSchoolAndDepartmentByName(String name) {
//        List<School> schoolList = schoolrepository.findByName(name);
//
//        if (!CollectionUtils.isEmpty(schoolList)) {
//            List<SchoolResponse> schoolResponseList = schoolList.stream().map(school -> mapSchoolResponse(school)).toList();
//
//            ResponseEntity<DepartmentResponse> responseEntity = restTemplate.getForEntity(DEPARTMENT_REST_ENDPOINT+"byName?deptName="+name, DepartmentResponse.class);
//            DepartmentResponse departmentResponse = responseEntity.getBody();
//
//            assert departmentResponse != null;
//            return mapSchoolAndDepartmentResponse(schoolResponseList, departmentResponse);
//        } else throw new SchoolNotFoundException("School and department not found for given name: " + name);
//    }

}
