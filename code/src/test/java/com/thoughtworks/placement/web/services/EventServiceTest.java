package com.thoughtworks.placement.web.services;

import com.thoughtworks.placement.web.model.Event;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.Mock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/placement-servlet-test.xml"})
public class EventServiceTest {
    @Autowired
    EventService eventService;

    @Mock
    MailService mailService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFind() throws Exception {
        Event events = eventService.find("00000000-0000-04d2-0000-00000000162f");
        assertNotNull(events);
        assertEquals("Company2",events.getCompany().getName());
    }

    @Test
    public void testGetAll() throws Exception {
        List<Event> events = (List<Event>)eventService.getAll();
        assertEquals(2,events.size());
    }

    @Test
    public void testNotifyStudents(){
        ReflectionTestUtils.setField(eventService,"mailService",mailService);
        Event event = new Event();

        eventService.notifyStudents(event);

        verify(mailService).notifyStudents(event);
    }
}
