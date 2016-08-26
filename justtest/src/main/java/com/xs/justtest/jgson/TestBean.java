package com.xs.justtest.jgson;

import java.io.Serializable;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-05-31 16:21
 * @email Xs.lin@foxmail.com
 */
public class TestBean implements Serializable {
    private static final String TAG = "TestBean";

    public String a;
    public String b;
    public int c;
    public int d;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c=" + c +
                ", d=" + d +
                '}';
    }
}
