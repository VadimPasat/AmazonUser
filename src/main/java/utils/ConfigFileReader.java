package utils;


import constant.DriverType;
import constant.OperatingSystem;

import java.io.*;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private final String PROPERTY_FILE_PATH = "src/main/resources/config.properties";

    public ConfigFileReader() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PROPERTY_FILE_PATH))) {
            properties = new Properties();
            properties.load(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("configuration.properties not found at " + PROPERTY_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if (!implicitlyWait.isEmpty()) {
            try {
                return Long.parseLong(implicitlyWait);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Not able to parse value : " + implicitlyWait + " int to Long");
            }
        }
        return 30;
    }

    public DriverType getBrowser() {
        try {
            String browserName = properties.getProperty("browser").toUpperCase();
            return DriverType.valueOf(browserName);
        } catch (NullPointerException e) {
            throw new RuntimeException("Missing browser property");
        }
    }

    public OperatingSystem getOperatingSystem() {
        String osName = properties.getProperty("operatingSystem").toUpperCase();
        if (osName.equalsIgnoreCase("win")) {
            return OperatingSystem.WINDOWS;
        } else if (osName.equalsIgnoreCase("mac")) {
            return OperatingSystem.MAC;
        } else if (osName.equalsIgnoreCase("nix") || osName.equalsIgnoreCase("nux") || osName.equalsIgnoreCase("linux")) {
            return OperatingSystem.LINUX;
        } else {
            return OperatingSystem.UNKNOWN;
        }
    }

    public Boolean getBrowserWindowSize() {
        String windowSize = properties.getProperty("windowMaximize");
        return Boolean.valueOf(windowSize);
    }
}
