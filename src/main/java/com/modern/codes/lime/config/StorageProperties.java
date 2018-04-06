package com.modern.codes.lime.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The type Storage properties.
 */
@ConfigurationProperties(prefix = "storage.properties")
public class StorageProperties {
    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(final String location) {
        this.location = location;
    }

    private String location;
}
