package com.burndy.algorithms.sort.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * @author dranfree
 * @since 2020.06.29
 */
@Builder
@AllArgsConstructor
public class Sample {

    public final int[] data;

    public final String name;

    public Sample copy() {
        int[] copy = new int[data.length];
        System.arraycopy(data, 0, copy, 0, data.length);
        return new Sample(copy, name);
    }
}