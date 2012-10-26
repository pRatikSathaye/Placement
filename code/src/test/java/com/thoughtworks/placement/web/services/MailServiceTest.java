package com.thoughtworks.placement.web.services;

import com.thoughtworks.placement.web.model.Company;
import com.thoughtworks.placement.web.model.Criteria;
import com.thoughtworks.placement.web.model.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/placement-servlet-test.xml"})
public class MailServiceTest {
    @Autowired
    MailService mailService;

    @Test
    public void testSendMail() throws Exception {
        String to="poojaak@thoughtworks.com,pooja.akshantal@gmail.com";

        Event event = new Event();
        event.setCompany(new Company("company", "description", new Criteria(60, 60, 60)));
        event.setEligibleStudents(to);
        mailService.notifyStudents(event);
    }
}
