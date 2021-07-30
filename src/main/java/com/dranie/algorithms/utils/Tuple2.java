package com.dranie.algorithms.utils;

import lombok.RequiredArgsConstructor;

/**
 * @author dingdong
 * @since 2021/4/23
 */
@RequiredArgsConstructor
public class Tuple2<T1, T2> {

    private final T1 _1;

    private final T2 _2;

    public static <U1, U2> Tuple2<U1, U2> of(U1 _1, U2 _2) {
        return new Tuple2<>(_1, _2);
    }

    public T1 _1() { return _1; }

    public T2 _2() { return _2; }
}
