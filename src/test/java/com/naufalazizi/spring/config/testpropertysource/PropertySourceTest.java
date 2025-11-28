package com.naufalazizi.spring.config.testpropertysource;

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
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("classpath:/test.properties")
@SpringBootTest(classes = PropertySourceTest.TestApplication.class)
public class PropertySourceTest {
    @Autowired
    TestApplication.SampleProperties sampleProperties;

    @Test
    void testSampleProperties(){

        Assertions.assertEquals("Sample Project Test", sampleProperties.getName());
        Assertions.assertEquals(1, sampleProperties.getVersion());
    }

    @SpringBootApplication
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
