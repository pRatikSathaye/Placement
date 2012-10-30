package com.thoughtworks.placement.web.dao;

import com.thoughtworks.placement.web.model.Role;
import com.thoughtworks.placement.web.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student, String> {

    Student findBySid(String sid);

    List<Student> findByFullName(String fullName);

    @Query("{\"marks.currentDegreeMarks\":{$gt:?0}}")
    List<Student> findByCurrentDegreeMarksGreaterThan(double marks);

    @Query("{\"marks.sscMarks\":{$gt:?0} , \"marks.hscMarks\":{$gt:?1}} , \"marks.currentDegreeMarks\":{$gt:?2}}")
    List<Student> findBySscMarksAndHscMarksAndCurrentDegreeMarksGreaterThan(double sscMarks,double hscMarks,double currentDegreeMarks);

    List<Student> findByRole(Role role);

    Page<Student> findByFullName(String fullName, Pageable pageable);

    List<Student> findBySidIn(Collection<String> sidList);
}
