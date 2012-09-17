package com.thoughtworks.placement.web.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Student {

    @Id
    private String SID;

    private String fullName;

    private String phoneNumber;
    private String password;
    private String email;
    private Marks marks;

    private Role role = Role.STUDENT;

    public Student(){
        this.role = Role.STUDENT;
    }

    public Student(String SID, String fullName, String phoneNumber, String password, String email, Marks marks, Role role) {
        this.SID = SID;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.marks = marks;
        this.role = role;
    }

    public String getSID() {
        return SID;
    }

    public Student setSID(String SID) {
        this.SID = SID;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Student setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Student setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Student setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Student setPassword(String password) {
        this.password = password;
        return this;
    }

    public Marks getMarks() {
        return marks;
    }

    public Student setMarks(Marks marks) {
        this.marks = marks;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public Student setRole(Role role) {
        this.role = role;
        return this;
    }
}
