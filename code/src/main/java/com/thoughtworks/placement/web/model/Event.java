package com.thoughtworks.placement.web.model;

import java.util.Date;
import java.util.List;

public class Event {
    private int eventId;
    private Date date;

    private Company company;

    private List<Student> students;

    private NotificationType notificationType;

    public Event() {
        date = new Date();
        company = new Company("","",new Criteria(0,0,0));
        notificationType = NotificationType.EMAIL;
    }

    public int getEventId() {
        return eventId;
    }

    public Event setEventId(int eventId) {
        this.eventId = eventId;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Event setDate(Date date) {
        this.date = date;
        return this;
    }

    public Company getCompany() {
        return company;
    }

    public Event setCompany(Company company) {
        this.company = company;
        return this;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Event setStudents(List<Student> students) {
        this.students = students;
        return this;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public Event setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
        return this;
    }
}
