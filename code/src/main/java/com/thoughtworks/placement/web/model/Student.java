package com.thoughtworks.placement.web.model;

public class Student {
    private String SID;

    private String firstName;
    private String middleName;
    private String lastName;

    private String email;
    private String contactNumber;

    public Student(String SID) {
        this.SID = SID;
    }

    public String getSID() {
        return SID;
    }

    public String getFirstName() {
        return firstName;
    }

    public Student setFirstName(String firstName) {
        this.firstName = firstName; return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Student setMiddleName(String middleName) {
        this.middleName = middleName; return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Student setLastName(String lastName) {
        this.lastName = lastName; return this;
    }

    public String getEmail() {
        return email;
    }

    public Student setEmail(String email) {
        this.email = email; return this;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public Student setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber; return this;
    }
}
