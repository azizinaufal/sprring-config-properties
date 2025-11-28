package com.naufalazizi.spring.config.propertysource;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = PropertySourceTest.TestApplication.class)
public class PropertySourceTest {
    @Autowired
    TestApplication.SampleProperties sampleProperties;

    @Test
    void testSampleProperties(){

        Assertions.assertEquals("Sample Project", sampleProperties.getName());
        Assertions.assertEquals(1, sampleProperties.getVersion());
    }

    @SpringBootApplication
    @PropertySource("classpath:/sample.properties")
    public static class TestApplication{

        @Component
        @Getter
        public static class SampleProperties{

            @Value("${sample.name}")
            private String name;

            @Value("${sample.version}")
            private Integer version;
        }

    }
}
