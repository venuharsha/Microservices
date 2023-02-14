package com.example.school.repository;

import com.example.school.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, String> {

    List<School> findByName(String name);
}
