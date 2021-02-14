package com.project.restservice.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailImpl implements Mail {

    private final JavaMailSender javaMailSender;


    @Autowired
    public MailImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendEmail(String email, String role) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Role change");
        msg.setText("Your role has been changed to " + role);

        javaMailSender.send(msg);

    }

    @Override
    public void sendEmailToReporter(String email, String task) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Task reporter");
        msg.setText("You are now the reporter of the task " + task);

        javaMailSender.send(msg);
    }

    @Override
    public void sendEmailToAssignee(String email, String task) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Task assignee");
        msg.setText("You are now the assignee of the task " + task);

        javaMailSender.send(msg);
    }

}
