package com.booking.support.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class ApplicationProperties {
    private String projectBasedir;

    public String getProjectBasedir() {
        return projectBasedir;
    }

    public void setProjectBasedir(String projectBasedir) {
        this.projectBasedir = projectBasedir;
    }
}
