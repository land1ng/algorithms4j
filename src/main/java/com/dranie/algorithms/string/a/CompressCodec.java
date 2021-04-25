package com.dranie.algorithms.string.a;

import com.dranie.algorithms.string.binary.BinaryIn;
import com.dranie.algorithms.string.binary.BinaryOut;
import com.dranie.algorithms.string.binary.Binarys;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 压缩/解压缩
 *
 * @author dingdong
 * @since 2021/4/25
 */
public interface CompressCodec {

    /**
     * 压缩
     *
     * @param bTn  输入流
     * @param bOut 输出流
     */
    void compress(BinaryIn bTn, BinaryOut bOut);

    default void compress(File in, File out) {
        try (BinaryIn bin = Binarys.newBinaryIn(in); BinaryOut bout = Binarys.newBinaryOut(out)) {
            compress(bin, bout);
        }
    }

    default void compress(InputStream in, OutputStream out) {
        try (BinaryIn bin = Binarys.newBinaryIn(in); BinaryOut bout = Binarys.newBinaryOut(out)) {
            compress(bin, bout);
        }
    }

    /**
     * 解压缩
     *
     * @param bTn  输入流
     * @param bOut 输出流
     */
    void uncompress(BinaryIn bTn, BinaryOut bOut);

    default void uncompress(File in, File out) {
        try (BinaryIn bin = Binarys.newBinaryIn(in); BinaryOut bout = Binarys.newBinaryOut(out)) {
            uncompress(bin, bout);
        }
    }

    default void uncompress(InputStream in, OutputStream out) {
        try (BinaryIn bin = Binarys.newBinaryIn(in); BinaryOut bout = Binarys.newBinaryOut(out)) {
            uncompress(bin, bout);
        }
    }
}
