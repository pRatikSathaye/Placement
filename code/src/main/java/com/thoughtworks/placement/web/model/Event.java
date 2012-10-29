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

    private String emailIdsOfEligibleStudents;

    private NotificationType notificationType;

    public Event() {
        eventId= UUID.randomUUID();
        date = new Date();
        emailIdsOfEligibleStudents ="";
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

    public String getEmailIdsOfEligibleStudents() {
        return emailIdsOfEligibleStudents;
    }

    public Event setEmailIdsOfEligibleStudents(String emailIdsOfEligibleStudents) {
        this.emailIdsOfEligibleStudents = emailIdsOfEligibleStudents;
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
