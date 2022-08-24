package com.burndy.algorithms.string.binary;

import java.io.Closeable;

/**
 * 二进制输入
 *
 * @author dingdong
 * @since 2021/4/25
 */
public interface BinaryIn extends Closeable {

    /**
     * 读取8位数据并返回一个char值
     *
     * @return char
     */
    char readChar();

    /**
     * 读取1-16位数据并返回一个char值
     *
     * @param r 位
     * @return char
     */
    char readChar(int r);

    byte readByte();

    short readShort();

    int readInt();

    int readInt(int r);

    long readLong();

    float readFloat();

    double readDouble();

    /**
     * 读取1位数据并返回一个boolean值
     *
     * @return boolean
     */
    boolean readBoolean();

    String readString();

    /**
     * 关闭输入流，确保不再读取。
     */
    @Override
    void close();

    /**
     * 比特流是否为空
     *
     * @return isEmpty
     */
    boolean isEmpty();
}
