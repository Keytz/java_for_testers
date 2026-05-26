package common;

import java.util.Random;
import java.io.File;


public class CommonFunctions {
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
}}
