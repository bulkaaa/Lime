package com.modern.codes.lime.service;

public interface IMailService {
    String prepareWelcomeEmailBody(String username, String password, String name, String surname);
}
