package com.thoughtworks.placement.web.model;

public class DummyStudent {
    private String sid ="12345";

    private String fullName="Shirish Padalkar";

    private String phoneNumber="9876543210";
    private String password="shirish";
    private String email="shirish4you@gmail.com";
    private Marks marks;


    public DummyStudent setSid(String sid) {
        this.sid = sid;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public DummyStudent setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public DummyStudent setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public DummyStudent setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DummyStudent setPassword(String password) {
        this.password = password;
        return this;
    }

    public Marks getMarks() {
        Marks marks= new Marks();
        marks.setSscMarks(76);
        marks.setHscMarks(80);
        marks.setCurrentDegreeMarks(96);
        return marks;
    }

    public DummyStudent setMarks(Marks marks) {
        this.marks = marks;
        return this;
    }

    public String getSid() {
        return sid;
    }
}
