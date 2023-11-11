package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnectionProperty {

    // Имя конфигурационного файла
    public static final String CONFIG_NAME = "Z:\\javaProjects\\damossJavaEE\\src\\main\\java\\config\\application.properties";
    // Свойства конфигурации
    public static final Properties PROPERTY_CONFIG = new Properties();

    public ConnectionProperty() throws IOException {
        PROPERTY_CONFIG.load(new FileInputStream(CONFIG_NAME));
    }

    // Получить значение параметра из конфигурации по имени свойства
    public static String getProperty(String property) {
        return PROPERTY_CONFIG.getProperty(property);
    }
}

