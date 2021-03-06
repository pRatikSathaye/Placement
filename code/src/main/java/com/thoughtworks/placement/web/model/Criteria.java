package com.thoughtworks.placement.web.model;

public class Criteria {
    private double sscMarks;
    private double hscMarks;
    private double currentDegreeMarks;

    public Criteria(double sscMarks, double hscMarks, double currentDegreeMarks) {
        this.sscMarks = sscMarks;
        this.hscMarks = hscMarks;
        this.currentDegreeMarks = currentDegreeMarks;
    }

    public double getSscMarks() {
        return sscMarks;
    }

    public double getHscMarks() {
        return hscMarks;
    }

    public double getCurrentDegreeMarks() {
        return currentDegreeMarks;
    }

    public void setSscMarks(double sscMarks) {
        this.sscMarks = sscMarks;
    }

    public void setHscMarks(double hscMarks) {
        this.hscMarks = hscMarks;
    }

    public void setCurrentDegreeMarks(double currentDegreeMarks) {
        this.currentDegreeMarks = currentDegreeMarks;
    }
}
