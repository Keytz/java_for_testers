package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class TestBase {
    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() throws IOException {
        if (app == null) {
            var properties = new Properties();
            properties.load(new FileReader(System.getProperty("target", "local.properties")));
            app = new ApplicationManager();
            app.init(System.getProperty("browser", "chrome"), properties);
        }

    }
    @AfterEach
    void checkDatabaseConsistency(){
        app.jdbc().checkConsistency();
    }

    public static String randomFile(String dir) {

        var files = new File(dir).listFiles(file ->
                file.isFile()
                        && file.getName().endsWith(".png")
        );

        var rnd = new Random();

        return files[rnd.nextInt(files.length)].getPath();
    }
}

