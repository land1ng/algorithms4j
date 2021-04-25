package com.dranie.algorithms.string.binary;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author dingdong
 * @since 2021/4/25
 */
@UtilityClass
public class Binarys {

    public static BinaryIn newStdIn() {
        return new BinaryInImpl();
    }

    public static BinaryIn newBinaryIn(File file) {
        return new BinaryInImpl(file);
    }

    public static BinaryIn newBinaryIn(InputStream source) {
        return new BinaryInImpl(source);
    }

    public static BinaryIn newBinaryIn(Socket socket) {
        return new BinaryInImpl(socket);
    }

    public static BinaryOut newStdOut() {
        return new BinaryOutImpl();
    }

    public static BinaryOut newBinaryOut(File file) {
        return new BinaryOutImpl(file);
    }

    public static BinaryOut newBinaryOut(OutputStream target) {
        return new BinaryOutImpl(target);
    }

    public static BinaryOut newBinaryOut(Socket socket) {
        return new BinaryOutImpl(socket);
    }
}
