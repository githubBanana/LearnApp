package com.xs.justtest.generics;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-05-23 14:01
 * @email Xs.lin@foxmail.com
 */
public class Contianer<K,V> {
    private static final String TAG = "Contianer";

    public Contianer(K k , V v) {
        this.k = k;
        this.v = v;
    }

    private K k;
    private V v;

    public K getK() {
        return k;
    }

    public void setK(K k) {
        this.k = k;
    }

    public V getV() {
        return v;
    }

    public void setV(V v) {
        this.v = v;
    }
}
