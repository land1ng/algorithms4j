package com.burndy.algorithms.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

/**
 * @author dranfree
 * @since 2020.06.06
 */
public abstract class IOUtil {

    public static final String SAMPLE_FILE_PATH = "samples/";

    public static File findSampleFile(String sampleName) {
        return new File(SAMPLE_FILE_PATH + sampleName);
    }

    public static File createRandomDataFile(int size) {
        Random rnd = new Random();
        int current = 0;
        File file = new File(IOUtil.SAMPLE_FILE_PATH + "topk/temp.txt");
        try (BufferedWriter bw = Files.newBufferedWriter(file.toPath())) {
            while (current++ < size) {
                bw.write(rnd.nextInt(100000) + "");
                if (current < size)
                    bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}