package com.hdbandit.starter_traceable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.hdbandit.traceable.Tracer;
import com.hdbandit.traceable.config.TraceableConfig;

@Configuration
@Import(TraceableConfig.class)
public class TraceableAutoConfiguration {

    @Bean(name = "default tracer")
    public Tracer defaultTracer() {
        return new Tracer() {         
            public void trace(String message) {
                System.out.println(message);
            }
        };
    }
    
}
