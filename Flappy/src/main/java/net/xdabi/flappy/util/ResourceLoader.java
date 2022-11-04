package net.xdabi.flappy.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceLoader {

    public static InputStream createInputStream(String fileName) {
        return ResourceLoader.class.getClassLoader().getResourceAsStream(fileName);
    }

    public static String loadShader(String fileName) {
        InputStream inputStream = createInputStream(fileName);

        StringBuilder shaderSource = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return shaderSource.toString();
    }
}
