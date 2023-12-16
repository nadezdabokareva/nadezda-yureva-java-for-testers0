package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Random;

public class TestBase {
    public static ApplicationManager app;

    @BeforeEach
    public void setUp() throws IOException {
        Properties properties = null;
        if (app == null) {
            properties = new Properties();
            properties.load(new FileReader(System.getProperty("target", "local.properties")));
            app = new ApplicationManager();
        }
        app.init(System.getProperty("browser", "firefox"), properties);
    }

    public static String randomFile(String direction) {
        var fileNames = new File(direction).list();
        var randomFile = new Random();
        var index = randomFile.nextInt(fileNames.length);
        return Paths.get(direction, fileNames[index]).toString();
    }

//    @AfterEach
//    public void tearDown() {
//        app.driver.close();
//    }
}
