package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class TestBase {
    public static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }
        app.init(System.getProperty("browser", "firefox"));
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
