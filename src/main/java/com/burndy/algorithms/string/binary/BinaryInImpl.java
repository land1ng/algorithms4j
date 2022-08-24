package com.burndy.algorithms.string.binary;

import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;

/**
 * @author dingdong
 * @since 2021/4/25
 */
class BinaryInImpl implements BinaryIn {

    private static final int EOF = -1;   // end of file

    private int n;                       // number of bits left in buffer
    private int buffer;                  // one character buffer

    private final BufferedInputStream in;

    public BinaryInImpl() {
        this.in = new BufferedInputStream(System.in);
        this.fillBuffer();
    }

    public BinaryInImpl(File file) {
        try {
            this.in = new BufferedInputStream(new FileInputStream(file));
            this.fillBuffer();
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException("file not found: " + file.getAbsolutePath(), e);
        }
    }

    public BinaryInImpl(Socket socket) {
        try {
            InputStream in = socket.getInputStream();
            this.in = new BufferedInputStream(in);
            this.fillBuffer();
        } catch (IOException e) {
            throw new UncheckedIOException("Could not open " + socket, e);
        }
    }

    public BinaryInImpl(InputStream in) {
        this.in = new BufferedInputStream(in);
        this.fillBuffer();
    }

    /**
     * 读取8位数据并返回一个char值
     *
     * @return char
     */
    @Override
    public char readChar() {

        if (isEmpty()) {
            throw new NoSuchElementException("Reading from empty input stream");
        }

        // special case when aligned byte
        if (n == 8) {
            int x = buffer;
            fillBuffer();
            return (char) (x & 0xff);
        }

        // combine last N bits of current buffer with first 8-N bits of new buffer
        int x = buffer;
        x <<= (8 - n);
        int oldN = n;
        fillBuffer();
        if (isEmpty()) {
            throw new NoSuchElementException("Reading from empty input stream");
        }
        n = oldN;
        x |= (buffer >>> n);
        return (char) (x & 0xff);
        // the above code doesn't quite work for the last character if N = 8
        // because buffer will be -1
    }

    /**
     * 读取1-16位数据并返回一个char值
     *
     * @param r 位
     * @return char
     */
    @Override
    public char readChar(int r) {
        if (r < 1 || r > 16) throw new IllegalArgumentException("Illegal value of r = " + r);

        // optimize r = 8 case
        if (r == 8) return readChar();

        char x = 0;
        for (int i = 0; i < r; i++) {
            x <<= 1;
            boolean bit = readBoolean();
            if (bit) x |= 1;
        }
        return x;
    }

    @Override
    public byte readByte() {
        char c = readChar();
        return (byte) (c & 0xff);
    }

    @Override
    public short readShort() {
        short x = 0;
        for (int i = 0; i < 2; i++) {
            char c = readChar();
            x <<= 8;
            x |= c;
        }
        return x;
    }

    @Override
    public int readInt() {
        int x = 0;
        for (int i = 0; i < 4; i++) {
            char c = readChar();
            x <<= 8;
            x |= c;
        }
        return x;
    }

    @Override
    public int readInt(int r) {
        if (r < 1 || r > 32) throw new IllegalArgumentException("Illegal value of r = " + r);

        // optimize r = 32 case
        if (r == 32) return readInt();

        int x = 0;
        for (int i = 0; i < r; i++) {
            x <<= 1;
            boolean bit = readBoolean();
            if (bit) x |= 1;
        }
        return x;
    }

    @Override
    public long readLong() {
        long x = 0;
        for (int i = 0; i < 8; i++) {
            char c = readChar();
            x <<= 8;
            x |= c;
        }
        return x;
    }

    @Override
    public float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    @Override
    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    /**
     * 读取1位数据并返回一个boolean值
     *
     * @return boolean
     */
    @Override
    public boolean readBoolean() {
        if (isEmpty()) throw new NoSuchElementException("Reading from empty input stream");
        n--;
        boolean bit = ((buffer >> n) & 1) == 1;
        if (n == 0) fillBuffer();
        return bit;
    }

    @Override
    public String readString() {

        if (isEmpty()) throw new NoSuchElementException("Reading from empty input stream");

        StringBuilder sb = new StringBuilder();
        while (!isEmpty()) {
            char c = readChar();
            sb.append(c);
        }
        return sb.toString();
    }

    private void fillBuffer() {
        try {
            buffer = in.read();
            n = 8;
        } catch (IOException e) {
            n = -1;
            buffer = EOF;
            throw new UncheckedIOException("" + EOF, e);
        }
    }

    /**
     * 关闭输入流，确保不再读取。
     */
    @Override
    public void close() {
        try {
            in.close();
        } catch (IOException e) {
            // ignore
        }
    }

    /**
     * 比特流是否为空
     *
     * @return isEmpty
     */
    @Override
    public boolean isEmpty() {
        return buffer == EOF;
    }
}
