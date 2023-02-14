package com.example.school;

import com.example.school.model.School;
import com.example.school.repository.SchoolRepository;
import com.example.school.service.SchoolService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
	void createSchoolAndDepartmentTest() {

	}

}
