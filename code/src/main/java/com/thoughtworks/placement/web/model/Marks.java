package com.thoughtworks.placement.web.model;

public class Marks {
    private double sscMarks;
    private double hscMarks;
    private double currentDegreeMarks;

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
