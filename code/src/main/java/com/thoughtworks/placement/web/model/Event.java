package com.thoughtworks.placement.web.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document
public class Event {
    @Id
    private UUID eventId;
    private Date date;

    private Company company;

    private String sidsOfEligibleStudents;

    private NotificationType notificationType;

    public Event() {
        eventId= UUID.randomUUID();
        date = new Date();
        sidsOfEligibleStudents ="";
        company = new Company("","",new Criteria(0,0,0));
        notificationType = NotificationType.EMAIL;
    }

    public UUID getEventId() {
        return eventId;
    }

    public Event setEventId(UUID eventId) {
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

    public String getSidsOfEligibleStudents() {
        return sidsOfEligibleStudents;
    }

    public Event setSidsOfEligibleStudents(String sidsOfEligibleStudents) {
        this.sidsOfEligibleStudents = sidsOfEligibleStudents;
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
