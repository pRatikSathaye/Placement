package com.thoughtworks.placement.web.dao;

import com.thoughtworks.placement.web.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student, String> {

    List<Student> findByFullName(String fullName);

    Page<Student> findByFullName(String fullName, Pageable pageable);
}