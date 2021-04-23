package com.dranie.algorithms.utils;

import lombok.RequiredArgsConstructor;

import java.util.Objects;

/**
 * @author dingdong
 * @since 2021/4/23
 */
@RequiredArgsConstructor
public class Tuple3<T1, T2, T3> {

    private final T1 _1;

    private final T2 _2;

    private final T3 _3;

    public static <U1, U2, U3> Tuple3<U1, U2, U3> of(U1 _1, U2 _2, U3 _3) {
        Objects.requireNonNull(_1, "_1 cannot be null!");
        Objects.requireNonNull(_2, "_2 cannot be null!");
        Objects.requireNonNull(_2, "_3 cannot be null!");
        return new Tuple3<>(_1, _2, _3);
    }

    public T1 _1() { return _1; }

    public T2 _2() { return _2; }

    public T3 _3() { return _3; }
}
