package com.thoughtworks.placement.web.services;

import com.thoughtworks.placement.web.dao.EventRepository;
import com.thoughtworks.placement.web.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    EventRepository repository;

    @Autowired
    MailService mailService;

    public void setRepository(EventRepository repository) {
        this.repository = repository;
    }

    public Event save(Event event) {
        return repository.save(event);
    }

    public Event find(String eventId) {
        return repository.findOne(eventId);
    }

    public Iterable<Event> getAll() {
        return repository.findAll();
    }

    public void notifyStudents(Event event) {
         mailService.notifyStudents(event);
    }
}
