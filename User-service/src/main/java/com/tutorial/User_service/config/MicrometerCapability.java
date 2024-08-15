package com.tutorial.User_service.config;

import feign.Capability;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicrometerCapability {
    @Bean
    public Capability capability(final MeterRegistry registry){
        return new feign.micrometer.MicrometerCapability(registry);
    }
}
