package com.dranie.algorithms;

import java.io.File;

/**
 * 算法相关的工具方法
 *
 * @author dranfree
 * @since 2020.06.01
 */
public abstract class Utils {

    private static final String SAMPLE_FILE_PATH = "samples/";

    /**
     * 获取测试数据文件 algos-data.zip
     *
     * @param filename
     * @return {@link File}
     */
    public static File locateSampleFile(String filename) {
        return new File(SAMPLE_FILE_PATH.concat(filename));
    }
}