package com.example.employeeservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thoughtworks.xstream.XStream;

@Configuration
public class AxonConfig {
 
    @Bean
    XStream xStream() {
        XStream xStream = new XStream();
      
        xStream.allowTypesByWildcard(new String[] {
                "com.example.**"
        });
        return xStream;
    }
}