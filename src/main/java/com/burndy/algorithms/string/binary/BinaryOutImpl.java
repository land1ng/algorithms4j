package com.burndy.algorithms.string.binary;

import java.io.*;
import java.net.Socket;

/**
 * @author dingdong
 * @since 2021/4/25
 */
@SuppressWarnings({ "DuplicatedCode", "PointlessBitwiseExpression" })
class BinaryOutImpl implements BinaryOut {

    private BufferedOutputStream out;  // the output stream

    private int n;                     // number of bits remaining in buffer
    private int buffer;                // 8-bit buffer of bits to write out

    public BinaryOutImpl() {
        out = new BufferedOutputStream(System.out);
    }

    public BinaryOutImpl(File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            out = new BufferedOutputStream(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BinaryOutImpl(OutputStream os) {
        out = new BufferedOutputStream(os);
    }

    public BinaryOutImpl(Socket socket) {
        try {
            OutputStream os = socket.getOutputStream();
            out = new BufferedOutputStream(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入指定的8位字符
     *
     * @param x a char to be written
     */
    @Override
    public void write(char x) {
        if (x >= 256) throw new IllegalArgumentException("Illegal 8-bit char = " + x);
        writeByte(x);
    }

    /**
     * 写入指定字符的低r(1-16)位
     * <p>
     * 适用于 byte(8bit)、short(16bit)、int(32bit)、long(64bit) 和 double(64bit) 的类似方法
     *
     * @param x a char to be written
     * @param r bit
     */
    @Override
    public void write(char x, int r) {
        if (r == 8) {
            write(x);
            return;
        }
        if (r < 1 || r > 16) throw new IllegalArgumentException("Illegal value for r = " + r);
        if (x >= (1 << r)) throw new IllegalArgumentException("Illegal " + r + "-bit char = " + x);
        for (int i = 0; i < r; i++) {
            boolean bit = ((x >>> (r - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    /**
     * 写入指定的比特
     *
     * @param x 布尔值
     */
    @Override
    public void write(boolean x) {
        writeBit(x);
    }

    @Override
    public void write(byte x) {
        writeByte(x & 0xff);
    }

    @Override
    public void write(int x) {
        writeByte((x >>> 24) & 0xff);
        writeByte((x >>> 16) & 0xff);
        writeByte((x >>> 8) & 0xff);
        writeByte((x >>> 0) & 0xff);
    }

    @Override
    public void write(int x, int r) {
        if (r == 32) {
            write(x);
            return;
        }
        if (r < 1 || r > 32) throw new IllegalArgumentException("Illegal value for r = " + r);
        if (x >= (1 << r)) throw new IllegalArgumentException("Illegal " + r + "-bit char = " + x);
        for (int i = 0; i < r; i++) {
            boolean bit = ((x >>> (r - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    @Override
    public void write(short x) {
        writeByte((x >>> 8) & 0xff);
        writeByte((x >>> 0) & 0xff);
    }

    @Override
    public void write(long x) {
        writeByte((int) ((x >>> 56) & 0xff));
        writeByte((int) ((x >>> 48) & 0xff));
        writeByte((int) ((x >>> 40) & 0xff));
        writeByte((int) ((x >>> 32) & 0xff));
        writeByte((int) ((x >>> 24) & 0xff));
        writeByte((int) ((x >>> 16) & 0xff));
        writeByte((int) ((x >>> 8) & 0xff));
        writeByte((int) ((x >>> 0) & 0xff));
    }

    @Override
    public void write(float x) {
        write(Float.floatToRawIntBits(x));
    }

    @Override
    public void write(double x) {
        write(Double.doubleToRawLongBits(x));
    }

    @Override
    public void write(String s) {
        for (int i = 0; i < s.length(); i++)
            write(s.charAt(i));
    }

    @Override
    public void write(String s, int r) {
        for (int i = 0; i < s.length(); i++)
            write(s.charAt(i), r);
    }

    /**
     * 关闭输出流，确保不再写入。
     * <p>
     * 至关重要，比特流的最后一个字节必须用0补齐以保证和文件系统的兼容性。
     */
    @Override
    public void close() {
        flush();
        try {
            out.close();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void flush() {
        try {
            out.flush();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Writes the specified bit to the binary output stream.
     *
     * @param x the bit
     */
    private void writeBit(boolean x) {
        // add bit to buffer
        buffer <<= 1;
        if (x) buffer |= 1;

        // if buffer is full (8 bits), write out as a single byte
        n++;
        if (n == 8) clearBuffer();
    }

    /**
     * Writes the 8-bit byte to the binary output stream.
     *
     * @param x the byte
     */
    private void writeByte(int x) {
        assert x >= 0 && x < 256;

        // optimized if byte-aligned
        if (n == 0) {
            try {
                out.write(x);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        // otherwise write one bit at a time
        for (int i = 0; i < 8; i++) {
            boolean bit = ((x >>> (8 - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    // write out any remaining bits in buffer to the binary output stream, padding with 0s
    private void clearBuffer() {
        if (n == 0) return;
        if (n > 0) buffer <<= (8 - n);
        try {
            out.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        n = 0;
        buffer = 0;
    }
}
