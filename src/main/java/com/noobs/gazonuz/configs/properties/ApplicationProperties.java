package com.noobs.gazonuz.configs.properties;

import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Service
public class ApplicationProperties {


    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileReader("/home/javohir/Desktop/2/gazon-uz/src/main/resources/application.properties"));
        } catch ( IOException e ) {
            throw new RuntimeException(e);
        }
    }


    public Properties getProperties() {
        return properties;
    }

}
