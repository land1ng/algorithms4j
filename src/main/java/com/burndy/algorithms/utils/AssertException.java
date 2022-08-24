package com.burndy.algorithms.utils;

/**
 * 断言异常
 *
 * @author dranfree
 * @since 2019.05.05
 */
public class AssertException extends RuntimeException {
    private static final long serialVersionUID = 6723388368533444910L;
    public AssertException() { }
    public AssertException(String message) { super(message); }
}