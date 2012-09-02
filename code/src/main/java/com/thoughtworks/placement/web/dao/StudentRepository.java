package com.thoughtworks.placement.web.dao;

import com.thoughtworks.placement.web.model.Role;
import com.thoughtworks.placement.web.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student, String> {

    Student findBySID(String sid);

    List<Student> findByFullName(String fullName);

    @Query("{\"marks.currentDegreeMarks\":{$gt:?0}}")
    List<Student> findByCurrentDegreeMarksGreaterThan(double marks);

    List<Student> findByRole(Role role);

    Page<Student> findByFullName(String fullName, Pageable pageable);
}
