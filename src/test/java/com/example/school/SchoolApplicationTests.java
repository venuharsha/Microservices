package com.example.school;

import com.example.school.dto.SchoolRequest;
import com.example.school.exception.SchoolNotFoundException;
import com.example.school.model.School;
import com.example.school.repository.SchoolRepository;
import com.example.school.service.SchoolService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class SchoolApplicationTests {

	@Autowired
	SchoolService schoolService;

	@MockBean
	SchoolRepository schoolRepository;

	@MockBean
	RestTemplate restTemplate;

	@Test
	void getAllSchoolsTest() {
		when(schoolRepository.findAll()).thenReturn(Stream.of(new School("1234", "St.Clarets", "Nallajerla", "SSC", "MPC", "Harsha")).toList());
		assertEquals(1, schoolService.getAllSchools().size());
	}

	@Test
	void createSchool() {
		doNothing().when(schoolRepository.save(any()));
		var school = new SchoolRequest();
		school.setName("Talent");
		String message = schoolService.createSchool(school);
		assertEquals("School created successfully", message);
	}

	@Test
	void createSchoolAndDepartment() {
		String s = "Department created successfully";

		when(schoolRepository.save(any())).thenReturn(new School("122","Test",null,null,null,null));
		when(restTemplate.postForEntity(anyString(),any(),any())).thenReturn(new ResponseEntity<>(s,HttpStatus.OK));
		var school = new SchoolRequest();
		school.setDeptName("MPC");
		school.setName("Talent");
		String message = schoolService.createSchoolAndDepartment(school);
		assertEquals("Both school and department created successfully.", message);
	}

	@Test
	void createSchoolAndDepartmentFail() {
		String s = "Department not created successfully";

		when(schoolRepository.save(any())).thenReturn(new School("122","Test",null,null,null,null));
		when(restTemplate.postForEntity(anyString(),any(),any())).thenReturn(new ResponseEntity<>(s,HttpStatus.OK));
		var school = new SchoolRequest();
		school.setDeptName("MPC");
		school.setName("Talent");
		String message = schoolService.createSchoolAndDepartment(school);
		assertEquals("School and department insertion issue", message);
		verify(schoolRepository, times(1)).save(any());
		verify(restTemplate,times(1)).postForEntity(anyString(),any(),any());
	}

	@Test
	void createSchoolAndDepartmentTimeoutScenario() {

		String s = "Department not created successfully";

		when(schoolRepository.save(any())).thenThrow(new RuntimeException("Timeout while connecting to db"));
		//when(restTemplate.postForEntity(anyString(),any(),any())).thenReturn(new ResponseEntity<>(s,HttpStatus.OK));
		var school = new SchoolRequest();
		school.setDeptName("MPC");
		school.setName("Talent");
		String message = schoolService.createSchoolAndDepartment(school);
	}

	@Test
	void getSchoolByIdTest() {
		when(schoolRepository.findById(anyString())).thenReturn(Optional.of(new School("12345", "Talent", null, null, null, null)));
		assertEquals("Talent", schoolService.getSchoolById("231152152").getName());
	}

	@Test
	void getSchoolByIdTest2() {
		when(schoolRepository.findById(anyString())).thenReturn(Optional.of(new School("1234", "Talent", null, null, null, null)));
		assertThrows(SchoolNotFoundException.class, () -> schoolService.getSchoolById(anyString()).getName());
	}

	@Test
	void getSchoolByNameTest() {
		when(schoolRepository.findByName(anyString())).thenReturn(Stream.of(new School("1234", "Spring", "Hyd", "SSC", null, null)).toList());
		assertEquals(1, schoolService.getSchoolByName(anyString()).size());
	}

	@Test
	void getSchoolByNameTest2() {
		when(schoolRepository.findByName("Spring")).thenReturn(Stream.of(new School("1234", "Talent", "Hyd", "SSC", null, null)).toList());
		assertThrows(SchoolNotFoundException.class, () -> schoolService.getSchoolByName("Talent"));
	}
}
