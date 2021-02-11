package com.project.restservice.mail;

public interface Mail {

    void sendEmail(String email, String role);

    void sendEmailToReporter(String email, String task);

    void sendEmailToAssignee(String email, String task);

}
