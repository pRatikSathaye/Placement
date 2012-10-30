package com.thoughtworks.placement.web.dao;

import com.thoughtworks.placement.annotations.StubRepository;
import com.thoughtworks.placement.web.model.Company;
import com.thoughtworks.placement.web.model.Criteria;
import com.thoughtworks.placement.web.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@StubRepository
public class EventRepositoryStub implements EventRepository{
    private List<Event> events = new ArrayList<Event>();

    public EventRepositoryStub(){
        Event event = new Event();
        event.setEventId(new UUID(1234,5678));
        event.setCompany(new Company("Company1", "description", new Criteria(60, 60, 60)));
        event.setSidsOfEligibleStudents("abc@some.com,xyz@some.com");
        events.add(event);

        event=new Event();
        event.setEventId(new UUID(1234,5679));
        event.setCompany(new Company("Company2","description",new Criteria(60,60,70)));
        event.setSidsOfEligibleStudents("abc@some.com,pqr@some.com");
        events.add(event);

    }
    @Override
    public Iterable<Event> findAll(Sort orders) {
        return null;
    }

    @Override
    public Page<Event> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Event save(Event event) {
        return null;
    }

    @Override
    public Iterable<Event> save(Iterable<? extends Event> events) {
        return null;
    }

    @Override
    public Event findOne(String eventId) {
        Event matchingEvent=null;
        for (Event event : events){
            if (eventId.equals(event.getEventId().toString())){
                matchingEvent=event;
            }
        }
        return matchingEvent;
    }

    @Override
    public boolean exists(String s) {
        return false;
    }

    @Override
    public Iterable<Event> findAll() {
        return events;
    }

    @Override
    public long count() {
        return events.size();
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void delete(Event event) {
    }

    @Override
    public void delete(Iterable<? extends Event> events) {
    }

    @Override
    public void deleteAll() {
    }
}
