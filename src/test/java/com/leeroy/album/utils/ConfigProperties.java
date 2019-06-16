package com.leeroy.album.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

    private static final Logger logger = LogManager.getLogger(ConfigProperties.class);

    public static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("src/test/resources/application.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            logger.error("Error while loading properties " + ex.getMessage());
        }
        return properties;
    }

}
