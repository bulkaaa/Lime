package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.ResourcePOJO;

public interface INotificationService {

    void checkUsedResources();

    Boolean checkAhead(ResourcePOJO res, String email, double use);
}
