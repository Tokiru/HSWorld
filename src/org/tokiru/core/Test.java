package org.tokiru.core;

/**
 * Created by tokiru.
 */
public class Test {
    public Test(int x) {
        this.x = x;
    }

    public Test setA(int a) {
        this.a = a;
        return this;
    }


    public Test setB(int b) {
        this.b = b;
        return this;
    }
    private int a, b, c, d, e, x;
}
