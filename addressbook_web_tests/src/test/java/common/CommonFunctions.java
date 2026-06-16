package common;

import java.util.Random;
import java.io.File;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CommonFunctions {
    public static String randomString(int n){
            var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(26);
            var result = Stream.generate(randomNumbers)
                    .limit(n)
                    .map(i -> 'a' + i)
                    .map(Character:: toString)
                    .collect(Collectors.joining());
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
