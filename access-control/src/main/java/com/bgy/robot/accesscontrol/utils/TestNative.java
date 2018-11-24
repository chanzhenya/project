package com.bgy.robot.accesscontrol.utils;

public class TestNative {

    static {
        System.loadLibrary("hello");
    }

    private native void sayHello();

    public static void main(String[] args) {
        new TestNative().sayHello();
    }
}
