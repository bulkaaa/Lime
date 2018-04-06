package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.ResourcePOJO;

/**
 * The interface Notification service.
 */
public interface INotificationService {

    /**
     * Check used resources.
     */
    void checkUsedResources();

    /**
     * Check ahead boolean.
     *
     * @param res   the res
     * @param email the email
     * @param use   the use
     * @return the boolean
     */
    Boolean checkAhead(ResourcePOJO res, String email, double use);
}
