package com.tutorial.User_service.config;

import brave.handler.MutableSpan;
import brave.handler.SpanHandler;
import brave.propagation.TraceContext;
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

    @Bean
    public SpanHandler spanHandler() {
        return new SpanHandler() {
            public boolean end(TraceContext context, MutableSpan span, Cause cause) {
                // Filtrar trazas no deseadas, como las de Eureka
                String url = span.tag("http.url");
                if (url != null && url.contains("/eureka/apps/")) {
                    return false; // No enviar esta traza a Zipkin
                }
                return true; // Enviar otras trazas normalmente
            }
        };
    }
}
