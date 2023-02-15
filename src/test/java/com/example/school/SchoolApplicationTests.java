package com.example.school;

import com.example.school.dto.SchoolRequest;
import com.example.school.exception.SchoolNotFoundException;
import com.example.school.model.School;
import com.example.school.repository.SchoolRepository;
import com.example.school.service.SchoolService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class SchoolApplicationTests {

	@Autowired
	SchoolService schoolService;

	@MockBean
	SchoolRepository schoolRepository;
	@Test
	void getAllSchoolsTest() {
		when(schoolRepository.findAll()).thenReturn(Stream.of(new School("1234", "St.Clarets", "Nallajerla", "SSC", "MPC", "Harsha")).toList());
		assertEquals(1, schoolService.getAllSchools().size());
	}

	@Test
	void createSchool() {
		var school = new SchoolRequest();
		school.setName("Talent");
		String message = schoolService.createSchool(school);
		assertEquals("School created successfully.", message);
	}

//	@Test
//	void createSchoolAndDepartment() {
//		var school = new SchoolRequest();
//		school.setDeptName("MPC");
//		String message = schoolService.createSchoolAndDepartment(school);
//		assertEquals("Both school and department created successfully.", message);
//	}

	@Test
	void getSchoolByIdTest() {
		when(schoolRepository.findById("12345")).thenReturn(Optional.of(new School("12345", "Talent", null, null, null, null)));
		assertEquals("Talent", schoolService.getSchoolById("12345").getName());
	}

	@Test
	void getSchoolByIdTest2() {
		when(schoolRepository.findById("12345")).thenReturn(Optional.of(new School("1234", "Talent", null, null, null, null)));
		assertThrows(SchoolNotFoundException.class, () -> schoolService.getSchoolById("1234").getName());
	}

}
