package com.optimagrowth.licensingservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "example")
@Configuration
@Getter @Setter
public class ServiceConfig {

    private String property;
}
