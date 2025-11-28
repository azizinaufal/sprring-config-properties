package com.naufalazizi.spring.config.resourceloader;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@SpringBootTest(classes = ResurceLoaderTest.TestApplication.class,properties = "spring.main.banner-mode=off")
public class ResurceLoaderTest {

    @Autowired
    private TestApplication.SampleResource sampleResource;

    @Test
    void testResourceLoader() throws Exception {
        Assertions.assertEquals("Mohamad Naufal Azizi", sampleResource.getText().trim());
    }

    @SpringBootApplication
    public static class TestApplication{
        public static void main(String[] args) {
            SpringApplication app = new SpringApplication(TestApplication.class);
            app.setBannerMode(Banner.Mode.OFF);
            app.run(args);
        }

        @Component
        public static class SampleResource implements ResourceLoaderAware {


            private ResourceLoader resourceLoader;
            @Override
            public void setResourceLoader(ResourceLoader resourceLoader) {
                this.resourceLoader=resourceLoader;
            }

            public String getText()throws Exception{
                Resource resource = resourceLoader.getResource("classpath:/text/resource.txt");
                try(var inputStream = resource.getInputStream()){
                    return new String(inputStream.readAllBytes());
                }
            }


        }
    }
}
