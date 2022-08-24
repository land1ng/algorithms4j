package com.burndy.algorithms.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * 秒表，性能测试。
 *
 * @author dranfree
 * @since 2020.05.30
 */
@SuppressWarnings("FieldCanBeLocal")
@Slf4j
public class Stopwatch {

    private long head;

    private long tail;

    private boolean stopped = false;
    private boolean started = false;

    private String tag;

    public void start(String tag) {
        if (stopped)
            throw new RuntimeException("计时已经结束！");
        head = System.currentTimeMillis();
        log.info("{}：计时开始！", tag);
        this.tag = tag;
        started = true;
    }

    public void finishReset() {
        if (!started)
            throw new RuntimeException("计时尚未开始！");
        tail = System.currentTimeMillis();
        log.info("{}：计时结束，经过了 {} 毫秒！", tag, (tail - head));
        stopped = true;
        reset();
    }

    private void reset() {
        head = 0;
        tail = 0;
        started = false;
        stopped = false;
    }
}