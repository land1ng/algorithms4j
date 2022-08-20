package com.dranie.algorithms;

import java.util.Scanner;

/**
 * @author RanYeah
 * @since 2022/8/20
 */
public class MeituanBarbecue {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int len = in.nextInt();

        in.nextLine();

        String s1 = in.nextLine();
        String s2 = in.nextLine();

        char[] value = new char[len * 2];

        for (int i = 0; i < len; i++) {
            value[i * 2] = s1.charAt(i);
            value[i * 2 + 1] = s2.charAt(i);
        }

        System.out.println(new String(value));
    }
}
