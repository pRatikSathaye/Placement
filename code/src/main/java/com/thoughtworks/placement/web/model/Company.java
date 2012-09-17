package com.thoughtworks.placement.web.model;

public class Company {
    private String name;
    private String description;

    private Criteria criteria;

    public Company(String name, String description, Criteria criteria) {
        this.name = name;
        this.description = description;
        this.criteria = criteria;
    }

    public String getName() {
        return name;
    }

    public Company setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Company setDescription(String description) {
        this.description = description;
        return this;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public Company setCriteria(Criteria criteria) {
        this.criteria = criteria;
        return this;
    }
}
