package com.github.jcapitanmoreno.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogRead {

    /**
     * Writes the provided object to a log file.
     *
     * @param obj The object to be written to the log file.
     * @param <T> The type of the object.
     */
    public static <T> void logWritter(T obj) {
        String rutaArchivo = "C:\\Users\\chaju\\Desktop\\log\\Log.txt";
        try {

            FileWriter fileWriter = new FileWriter(rutaArchivo, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(obj.toString());
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
