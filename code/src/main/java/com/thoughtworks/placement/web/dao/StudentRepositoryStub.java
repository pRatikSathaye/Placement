package com.thoughtworks.placement.web.dao;

import com.thoughtworks.placement.annotations.StubRepository;
import com.thoughtworks.placement.web.model.Marks;
import com.thoughtworks.placement.web.model.Role;
import com.thoughtworks.placement.web.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@StubRepository
public class StudentRepositoryStub implements StudentRepository {

    private List<Student> students = new ArrayList<Student>();

    public StudentRepositoryStub(){
        Marks marks = new Marks(100,100,100);
        Student student = new Student("shirish", "Shirish Padalkar", "7709032007", "shirish", "shirish4you@gmail.com", marks, Role.PLACEMENT_OFFICER);
        students.add(student);
        marks = new Marks(70,80,90);
        student = new Student("gurpreet", "Gurpreet Luthra", "9876543210", "gurpreet", "gurpreet@gmail.com", marks, Role.STUDENT);
        students.add(student);
        marks = new Marks(70,80,40);
        student = new Student("test", "Test User", "9876543211", "test", "test@gmail.com", marks, Role.STUDENT);
        students.add(student);
    }

    @Override
    public Student save(Student student) {
        return null;
    }

    @Override
    public Iterable<Student> save(Iterable<? extends Student> students) {
        return null;
    }

    @Override
    public Student findOne(String username){
        for (Student student : students){
            if (student.getSid().equals(username)){
                return student;
            }
        }
        return null;
    }

    @Override
    public boolean exists(String s) {
        return false;
    }

    @Override
    public Iterable<Student> findAll() {
        return students;
    }

    @Override
    public long count() {
        return students.size();
    }

    @Override
    public void delete(String s) {
      
    }

    @Override
    public void delete(Student student) {
      
    }

    @Override
    public void delete(Iterable<? extends Student> students) {
      
    }

    @Override
    public void deleteAll() {
      
    }

    @Override
    public Student findBySid(String sid) {
        for (Student student : students){
            if (student.getSid().equals(sid)){
                return student;
            }
        }
        return null;
    }

    @Override
    public List<Student> findByFullName(String fullName) {
        List<Student> found = new ArrayList<Student>();
        for (Student student : students){
            if (student.getFullName().equals(fullName)){
                found.add(student);
            }
        }
        return found;
    }

    @Override
    public List<Student> findByCurrentDegreeMarksGreaterThan(double marks) {
        List<Student> found = new ArrayList<Student>();
        for (Student student : students){
            if (student.getMarks().getCurrentDegreeMarks()>=marks){
                found.add(student);
            }
        }
        return found;
    }

    @Override
    public List<Student> findBySscMarksAndHscMarksAndCurrentDegreeMarksGreaterThan(double sscMarks, double hscMarks, double currentDegreeMarks) {
        List<Student> found = new ArrayList<Student>();
        for (Student student : students){
            Marks studentMarks = student.getMarks();
            if (studentMarks.getSscMarks()>= sscMarks && studentMarks.getHscMarks() >= hscMarks && studentMarks.getCurrentDegreeMarks()>=currentDegreeMarks ){
                found.add(student);
            }
        }
        return found;
    }

    @Override
    public List<Student> findByRole(Role role) {
        List<Student> found = new ArrayList<Student>();
        for (Student student : students){
            if (student.getRole() == role){
                found.add(student);
            }
        }
        return found;
    }

    @Override
    public Page<Student> findByFullName(String fullName, Pageable pageable) {
        return null;
    }

    @Override
    public List<Student> findBySidIn(Collection<String> sidList) {
        List<Student> found = new ArrayList<Student>();
        for (Student student : students){
            if (sidList.contains(student.getSid())){
                found.add(student);
            }
        }
        return found;
    }

    @Override
    public Iterable<Student> findAll(Sort orders) {
        return null;
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return null;
    }
}
