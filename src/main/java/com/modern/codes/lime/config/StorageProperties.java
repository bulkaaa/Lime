package com.modern.codes.lime.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "storage.properties")
public class StorageProperties {
    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }
    private String location;
}
