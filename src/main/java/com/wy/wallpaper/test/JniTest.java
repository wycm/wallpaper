package com.wy.wallpaper.test;

/**
 * Created by wy on 11/21/2016.
 */
public class JniTest {
    static {
        System.loadLibrary("goodluck");
    }
    public native static int get();
    public native static void set(int i);
    public static void main(String[] args) {
        JniTest test = new JniTest();
        test.set(10);
        System.out.println(test.get());
    }
}
