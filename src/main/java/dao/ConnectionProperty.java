package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * Класс формирования свойств соединения с БД по конфигурационному
 файлу
 */
public class ConnectionProperty {

    // Имя конфигурационного файла
    public static final String CONFIG_NAME = "Z:\\javaProjects\\damossJavaEE\\src\\main\\java\\config\\application.properties";
    // Свойства конфигурации
    public static final Properties PROPERTY_COFIG = new Properties();
    public ConnectionProperty() throws FileNotFoundException, IOException {
        PROPERTY_COFIG.load(new FileInputStream(CONFIG_NAME));
    }
    // Получить значение параметра из конфигурации по имени свойства
    public static String getProperty(String property) {
        return PROPERTY_COFIG.getProperty(property);
    }
}

