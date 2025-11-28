package com.naufalazizi.spring.config.configurationproperties;

import com.naufalazizi.spring.config.converter.StringToDateConverter;
import com.naufalazizi.spring.config.properties.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

@SpringBootTest(classes = ConfigurationPropertiesTest.TestApplication.class)
public class ConfigurationPropertiesTest {
    
    @Autowired
    private ConversionService conversionService;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Test
    void testConversionService() {
        Assertions.assertTrue(conversionService.canConvert(String.class,Duration.class));
        Assertions.assertTrue(conversionService.canConvert(String.class,Date.class));

        Duration result = conversionService.convert("10s", Duration.class);
        Assertions.assertEquals(Duration.ofSeconds(10),result);
    }

    @Test
    void testConfigurationProperties() {

        Assertions.assertEquals("spring-config-properties",applicationProperties.getName());
        Assertions.assertEquals(1,applicationProperties.getVersion());
        Assertions.assertEquals(false,applicationProperties.isProductionMode());

    }

    @Test
    void testDatabaseProperties() {
        Assertions.assertEquals("belajar",applicationProperties.getDatabase().getDatabase());
        Assertions.assertEquals("azizi",applicationProperties.getDatabase().getUsername());
        Assertions.assertEquals("azizi",applicationProperties.getDatabase().getPassword());
        Assertions.assertEquals("jdbc:contoh",applicationProperties.getDatabase().getUrl());
    }

    @Test
    void testCollection() {
        Assertions.assertEquals(Arrays.asList("products", "categories", "customers"),applicationProperties.getDatabase().getWhitelistTables());
        Assertions.assertEquals(100, applicationProperties.getDatabase().getMaxTableSize().get("products"));
        Assertions.assertEquals(100, applicationProperties.getDatabase().getMaxTableSize().get("categories"));
        Assertions.assertEquals(100, applicationProperties.getDatabase().getMaxTableSize().get("customers"));
    }


    @Test
    void testEmbededCollection() {
        Assertions.assertEquals("default", applicationProperties.getDefaultRoles().get(0).getId());
        Assertions.assertEquals("Default Role", applicationProperties.getDefaultRoles().get(0).getName());
        Assertions.assertEquals("guest", applicationProperties.getDefaultRoles().get(1).getId());
        Assertions.assertEquals("Guest Role", applicationProperties.getDefaultRoles().get(1).getName());

        Assertions.assertEquals("admin", applicationProperties.getRoles().get("admin").getId());
        Assertions.assertEquals("Admin Role", applicationProperties.getRoles().get("admin").getName());
        Assertions.assertEquals("finance", applicationProperties.getRoles().get("finance").getId());
        Assertions.assertEquals("Finance Role", applicationProperties.getRoles().get("finance").getName());
    }

    @Test
    void testDuration() {
        Assertions.assertEquals(Duration.ofSeconds(10),applicationProperties.getDefaultTimeout());
    }

    @Test
    void testCustomConverter() {
        Date expiredDate  = applicationProperties.getExpiredDate();
        var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Assertions.assertEquals("2025-11-29",dateFormat.format(expiredDate));
    }

    @SpringBootApplication
    @EnableConfigurationProperties({
            ApplicationProperties.class
    })
    @Import({StringToDateConverter.class})
    public static class TestApplication{

        @Bean
        public ConversionService conversionService(StringToDateConverter stringToDateConverter){
            ApplicationConversionService applicationConversionService = new ApplicationConversionService();
            applicationConversionService.addConverter(stringToDateConverter);
            return applicationConversionService;
        }

    }
}
