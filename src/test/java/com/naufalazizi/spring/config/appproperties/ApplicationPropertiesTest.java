package com.naufalazizi.spring.config.appproperties;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
public class ApplicationPropertiesTest {

    @Autowired
    private Environment environment;

    @Test
    void testApplicationProperties() {
        String appName = environment.getProperty("spring.application.name");
        Assertions.assertEquals("spring-config-properties", appName);
    }

    @SpringBootApplication
    public static class TestApplication{

    }
}
