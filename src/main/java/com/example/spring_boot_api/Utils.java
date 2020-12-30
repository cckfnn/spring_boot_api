package com.example.spring_boot_api;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Utils {
    public static String loadConfigAsString(String fileName) {
        String result = null;
        ClassLoader cl = Utils.class.getClassLoader();
        try (InputStream inputStream = cl.getResourceAsStream(fileName)) {
            assert inputStream != null;
            result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
