package com.thoughtworks.placement.web.services;


import com.thoughtworks.placement.web.model.Company;
import com.thoughtworks.placement.web.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class MailService {
    @Autowired
    private MailSender mailSender;

    private void sendMail(String from, String to[], String subject, String msg) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);
    }

    public void notifyStudents(Event event, List<String> emailIds) {
        String messageBody = createMessageBody(event.getCompany(), event.getDate());
        sendMail("dummy@dummy.com", emailIds.toArray(new String[emailIds.size()]),"About Campus",messageBody);
    }

    private String createMessageBody(Company company, Date date) {
        return company.getName()+ " is going to visit out college on " +date
               + "\n\n" +
               "Regards,\n" +
               "Placement officer";
    }
}
