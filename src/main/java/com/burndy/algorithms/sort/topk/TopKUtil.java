package com.burndy.algorithms.sort.topk;

import com.burndy.algorithms.utils.IOUtil;
import com.burndy.algorithms.utils.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * TopK 问题
 *
 * @author dranfree
 * @since 2020.06.06
 */
@Slf4j
public abstract class TopKUtil {


    public static int[] max(File source, int k) {
        return new HeapTopK.MaxHeapTopK().fetch(source, k);
    }

    public static int[] min(File source, int k) {
        return new HeapTopK.MinHeapTopK().fetch(source, k);
    }


    public static void main(String[] args) {
        Stopwatch watch = new Stopwatch();
        watch.start("创建数据文件");
        File source = IOUtil.createRandomDataFile(10000000);
//        File source = IOUtil.findSampleFile("topk/small.txt");
        watch.finishReset();
        watch.start("TopK!");
        int[] topk = max(source, 1000);
        watch.finishReset();
        log.info("TopK: {}", topk);
    }
}