package com.bjz.naturescape.utils;

/**
 * Created by bjz on 11/6/2017.
 */

public class Objects {
    public static void requireNonNull(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }
}
