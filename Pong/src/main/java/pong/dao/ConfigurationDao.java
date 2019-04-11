package pong.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class ConfigurationDao {

    public static Properties properties;
    public static String fileName;

    public static HashMap<String, String> initConfigurations(String file) {
        fileName = file;
        HashMap<String, String> configs = null;
        properties = new Properties();
        try {
            InputStream input = new FileInputStream(fileName);
            properties.load(input);
        } catch (Exception e) {
            initProperties();
        }
        configs = fillHashMap();

        return configs;
    }

    public static HashMap<String, String> fillHashMap() {
        HashMap<String, String> configs = new HashMap<>();
        try {
            Enumeration<?> e = properties.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = properties.getProperty(key);
                configs.put(key, value);
            }
        } catch (Exception e) {
        }
        return configs;
    }

    public static void initProperties() {
        try {
            OutputStream output = new FileOutputStream(fileName);
            properties.setProperty("difficulty", "1");
            properties.setProperty("pause", "P");
            properties.setProperty("menu", "M");
            properties.setProperty("Player1_Up", "W");
            properties.setProperty("Player1_Down", "S");
            properties.setProperty("Player2_Up", "Up");
            properties.setProperty("Player2_Down", "Down");
            properties.setProperty("endingpoint", "5");
            properties.setProperty("speedUp", "1");
            properties.setProperty("BallSpeed", "8");
            properties.setProperty("BatSpeed", "4");
            properties.setProperty("powerups", "0");
            properties.store(output, null);
        } catch (Exception f) {
        }
    }

    public static HashMap<String, String> resetConfigurations() {
        initProperties();
        return fillHashMap();
    }

    public static void setProperty(String key, String value) {
        OutputStream output = null;
        try {
            output = new FileOutputStream(fileName);
            properties.setProperty(key, value);
            properties.store(output, null);
        } catch (Exception e) {
        }

    }

}
