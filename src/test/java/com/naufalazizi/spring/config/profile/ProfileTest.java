package com.naufalazizi.spring.config.profile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ProfileTest.TestApplication.class)
@ActiveProfiles({"production"})
public class ProfileTest {

    @Autowired
    private TestApplication.sayHello sayHello;

    @Test
    void testProfile() {
        Assertions.assertEquals("Say Hello Azizi From Production", sayHello.say("Azizi"));
    }

    @SpringBootApplication
    public  static class TestApplication{

        public interface sayHello{
            String say(String name);
        }

        @Component
        @Profile("local")
        public static class sayHelloLocal implements sayHello{

            @Override
            public String say(String name) {
                return "Say Hello " + name + " From Local";
            }
        }

        @Component
        @Profile("production")
        public static class sayHelloProduction implements sayHello{
            @Override
            public String say(String name) {
                return "Say Hello " + name + " From Production";
            }
        }

    }
}
