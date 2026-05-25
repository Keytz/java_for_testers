package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.util.Random;

public class TestBase {
    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }
        app.init(System.getProperty("browser", "firefox"));
    }
public static String randomString(int n){
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++) {
            result = result + (char)('a'+ rnd.nextInt(26));
        }

        return result;
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

