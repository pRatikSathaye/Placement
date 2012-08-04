package com.thoughtworks.placement.web.model;

public class Marks {
    private double sscMarks;
    private double hscMarks;
    private double currentDegreeMarks;

    public Marks(double sscMarks, double hscMarks, double currentDegreeMarks) {
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
}
