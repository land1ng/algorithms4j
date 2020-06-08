package com.dranie.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 输入工具类
 *
 * @author dran
 * @since 2020-06-08
 */
public abstract class InputUtil {

    public static final BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));

    public static int readInt() {
        return Integer.parseInt(line());
    }

    private static String line() {
        try {
            return scan.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
