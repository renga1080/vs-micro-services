package com.ranga.vs.util;

import org.springframework.boot.builder.SpringApplicationBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class VSUtil {

    private static final String OS_NAME = System.getProperty("os.name");
    private static final String WINDOWS = "Windows";

    public static boolean isLocal() {
        return OS_NAME.startsWith(WINDOWS);
    }

    public static void prepareLocal() {
        if (isLocal()) {
            System.setProperty("spring.profiles.active", "local");
        }
    }

    public static void prepareTomcat(SpringApplicationBuilder springApplicationBuilder) {

        try {
            System.setProperty("HOST", InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            throw new RuntimeException("Failed to find host");
        }


        try (FileInputStream fis = new FileInputStream("/local/apps/abc.tx")) {
            Properties vsProperties = new Properties();
            vsProperties.load(fis);
            for (String key : vsProperties.stringPropertyNames()) {
                String value = vsProperties.getProperty(key);
                System.setProperty(key, value);
            }


        } catch (IOException ie) {
            throw new RuntimeException("Failed to load property file from location xxxxx");
        }


        // can read a property file from the server and set them in System before setting the profile
        springApplicationBuilder.profiles(System.getProperty("env"));
    }

}
