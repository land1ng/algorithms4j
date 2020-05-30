package com.dranie.algorithms.utils;

/**
 * @author dranfree
 * @since 2020.05.30
 */
public abstract class Assert {

    public static void isTrue(boolean expression) {
        if (!expression)
            throw new AssertException("expression not true");
    }

    public static void isFalse(boolean expression) {
        if (expression)
            throw new AssertException("expression not false");
    }

    public static void notNull(Object object) {
        if (object == null)
            throw new AssertException("is null");
    }

    public static void isNull(Object object) {
        if (object != null)
            throw new AssertException("not null");
    }

    public static void isEquals(Object o1, Object o2) {
        if (o1 == null || o2 == null) {
            if (o1 != o2)
                throw new AssertException("o1 is null or o2 is null");
        } else {
            if (!o1.equals(o2))
                throw new AssertException("o1 not equals to o2");
        }
    }

    public static void notEquals(Object o1, Object o2) {
        if (o1 == null || o2 == null) {
            if (o1 == o2)
                throw new AssertException("o1 and o2 both null");
        } else {
            if (o1.equals(o2))
                throw new AssertException("o1 equals to o2");
        }
    }
}