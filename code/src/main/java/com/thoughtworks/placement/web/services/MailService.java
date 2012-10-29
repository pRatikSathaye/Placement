package com.thoughtworks.placement.web.services;


import com.thoughtworks.placement.web.model.Company;
import com.thoughtworks.placement.web.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Date;

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

    public void notifyStudents(Event event) {
        String messageBody = createMessageBody(event.getCompany(), event.getDate());
        String[] to = event.getEmailIdsOfEligibleStudents().split(",");
        sendMail("dummy@dummy.com", to,"About Campus",messageBody);
    }

    private String createMessageBody(Company company, Date date) {
        return company.getName()+ " is going to visit out college on " +date
               + "\n\n" +
               "Regards,\n" +
               "Placement officer";
    }
}
