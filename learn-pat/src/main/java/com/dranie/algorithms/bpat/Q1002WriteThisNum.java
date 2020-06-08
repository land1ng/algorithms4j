package com.dranie.algorithms.bpat;

import com.dranie.algorithms.InputUtil;

import java.util.Stack;

/**
 * 读入一个正整数 n，计算其各位数字之和，用汉语拼音写出和的每一位数字。
 *
 *
 *
 *
 * @author dranfree
 * @since 2020.06.08
 */
public class Q1002WriteThisNum {
    public static void main(String[] args) {
        String line = InputUtil.readString();
        long sum = 0;
        for (int i = 0; i < line.length(); i++)
            sum += Integer.parseInt(line.substring(i, i + 1));
        Stack<Integer> stack = new Stack<>();
        while (sum > 0) {
            stack.push((int) (sum % 10));
            sum /= 10;
        }
        while (true) {
            System.out.print(toPinyin(stack.pop()));
            if (!stack.empty())
                System.out.print(" ");
            else
                break;
        }
    }

    private static String toPinyin(int n) {
        switch (n) {
            case 0: return "ling";
            case 1: return "yi";
            case 2: return "er";
            case 3: return "san";
            case 4: return "si";
            case 5: return "wu";
            case 6: return "liu";
            case 7: return "qi";
            case 8: return "ba";
            case 9: return "jiu";
        }
        return "";
    }
}