package com.dranie.algorithms.string;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 这份 API 定义了一个构造函数，它用一个含有 R 个字符的字符串参数指定了字母表。API 定
 * 义了 {@code toChar()} 方法和 {@code toIndex()} 方法来在字符和 0 到 R-1 之间的整型值进行转换（常数时间）。
 *
 * @author dingdong
 * @since 2021/4/26
 */
public class Alphabet {

    /**
     * character => index
     */
    private final int[] inverse;       // indices

    /**
     * index => character
     */
    private final char[] alphabet;     // the characters in the alphabet
    private final int R;               // the radix of the alphabet

    public static final Alphabet BINARY = new Alphabet("01");

    public static final Alphabet DECIMAL = new Alphabet("0123456789");

    public static final Alphabet HEXADECIMAL = new Alphabet("0123456789ABCDEF");

    public static final Alphabet LOWERCASE = new Alphabet("abcdefghijklmnopqrstuvwxyz");

    public static final Alphabet UPPERCASE = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    public static final Alphabet BASE64 = new Alphabet(
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwx z0123456789+/");

    public static final Alphabet ASCII = new Alphabet(128);

    /**
     * 根据输入字符串s中的字符构造一个字母表
     *
     * @param alpha 字符串
     */
    public Alphabet(String alpha) {
        // 不能有重复的字符
        boolean[] unicode = new boolean[Character.MAX_VALUE];
        for (int i = 0; i < alpha.length(); i++) {
            char c = alpha.charAt(i);
            if (unicode[c]) {
                throw new IllegalArgumentException("Illegal alphabet: repeated character = '" + c + "'");
            }
            unicode[c] = true;
        }
        alphabet = alpha.toCharArray();
        R = alpha.length();
        inverse = new int[Character.MAX_VALUE];
        Arrays.fill(inverse, -1);
        // can't use char since R can be as big as 65,536
        for (int c = 0; c < R; c++)
            inverse[alphabet[c]] = c;
    }

    /**
     * Initializes a new alphabet using characters 0 through R-1.
     *
     * @param radix the number of characters in the alphabet (the radix R)
     */
    private Alphabet(int radix) {
        this.R = radix;
        alphabet = new char[R];
        inverse = new int[R];
        // can't use char since R can be as big as 65,536
        for (int i = 0; i < R; i++)
            alphabet[i] = (char) i;
        for (int i = 0; i < R; i++)
            inverse[i] = i;
    }

    public char toChar(int index) {
        return alphabet[index];
    }

    public int toIndex(char c) {
        if (c >= inverse.length || inverse[c] == -1) {
            throw new IllegalArgumentException("Character " + c + " not in alphabet");
        }
        return inverse[c];
    }

    public boolean contains(char c) {
        return inverse[c] != -1;
    }

    public int radix() {
        return R;
    }

    /**
     * Returns the binary logarithm of the number of characters in this alphabet.
     *
     * @return log2(R)
     */
    public int lgR() {
        int lgR = 0;
        for (int t = R - 1; t >= 1; t /= 2)
            lgR++;
        return lgR;
    }

    public int[] toIndices(String s) {
        char[] source = s.toCharArray();
        return IntStream.range(0, source.length).map(i -> toIndex(source[i])).toArray();
    }

    public String toChars(int[] indices) {
        StringBuilder s = new StringBuilder(indices.length);
        Arrays.stream(indices).mapToObj(this::toChar).forEach(s::append);
        return s.toString();
    }
}
