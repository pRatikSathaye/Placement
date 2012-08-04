package com.thoughtworks.placement.web.model;

public class Student {
    private String SID;

    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private Marks marks;

    public Student(String SID) {
        this.SID = SID;
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


    public String getSID() {
        return SID;
    }
}
