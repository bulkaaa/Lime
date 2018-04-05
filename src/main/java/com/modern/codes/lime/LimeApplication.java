package com.modern.codes.lime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.modern.codes.lime.config.StorageProperties;

/**
 * The type Lime application.
 */
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class LimeApplication {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(final String[] args) {
        SpringApplication.run(LimeApplication.class, args);
    }
}
