package com.dranie.algorithms.string.binary;

import java.io.Closeable;

/**
 * @author dingdong
 * @since 2021/4/25
 */
public interface BinaryOut extends Closeable {

    /**
     * 写入指定的8位字符
     *
     * @param x a char to be written
     */
    void write(char x);

    /**
     * 写入指定字符的低r(1-16)位
     * <p>
     * 适用于 byte(8bit)、short(16bit)、int(32bit)、long(64bit) 和 double(64bit) 的类似方法
     *
     * @param x a char to be written
     * @param r bit
     */
    void write(char x, int r);

    /**
     * 写入指定的比特
     *
     * @param x 布尔值
     */
    void write(boolean x);

    void write(byte x);

    void write(int x);

    void write(int x, int r);

    void write(short x);

    void write(long x);

    void write(float x);

    void write(double x);

    void write(String s);

    void write(String s, int r);

    /**
     * 关闭输出流，确保不再写入。
     * <p>
     * 至关重要，比特流的最后一个字节必须用0补齐以保证和文件系统的兼容性。
     */
    @Override
    void close();

    void flush();
}
