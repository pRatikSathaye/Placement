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

    public Marks setSscMarks(double sscMarks) {
        this.sscMarks = sscMarks;
        return this;
    }

    public Marks setHscMarks(double hscMarks) {
        this.hscMarks = hscMarks;
        return this;
    }

    public Marks setCurrentDegreeMarks(double currentDegreeMarks) {
        this.currentDegreeMarks = currentDegreeMarks;
        return this;
    }
}
