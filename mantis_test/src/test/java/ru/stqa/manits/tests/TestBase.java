package ru.stqa.manits.tests;

import org.junit.jupiter.api.BeforeEach;
import ru.stqa.manits.manager.ApplicationManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

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
}
