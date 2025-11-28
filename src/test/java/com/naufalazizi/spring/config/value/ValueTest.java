package com.naufalazizi.spring.config.value;

import com.naufalazizi.spring.config.environment.EnvironmentTest;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest(classes = ValueTest.TestApplciation.class)
public class ValueTest {

    @Autowired
    private TestApplciation.ApplicationProperties applicationProperties;

    @Autowired
    private TestApplciation.SystemEnvironment systemEnvironment;

    @Test
    void testValue() {
        Assertions.assertEquals("spring-config-properties", applicationProperties.getName());
        Assertions.assertEquals(1, applicationProperties.getVersion());
        Assertions.assertEquals(false, applicationProperties.isProductionMode());
    }

    @Test
    void testSystemEnvironment(){
        Assertions.assertEquals("C:\\Program Files\\Java\\jdk-17",systemEnvironment.getJavahome() );
    }

    @SpringBootApplication
    public static class TestApplciation{

        @Component
        @Getter
        public static class SystemEnvironment{

            @Value("${JAVA_HOME}")
            private String javahome;

        }

        @Component
        public static class ApplicationProperties{

            @Value("${spring.application.name}")
            @Getter
            private String name;

            @Value("${application.version}")
            @Getter
            private Integer version;

            @Value("${application.production-mode}")
            @Getter
            private boolean productionMode;

        }
    }
}
