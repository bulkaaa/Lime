package com.modern.codes.lime.service;

/**
 * The interface Mail service.
 */
public interface IMailService {
    /**
     * Prepare welcome email body string.
     *
     * @param username the username
     * @param password the password
     * @param name     the name
     * @param surname  the surname
     * @return the string
     */
    String prepareWelcomeEmailBody(String username, String password, String name, String surname);
}
