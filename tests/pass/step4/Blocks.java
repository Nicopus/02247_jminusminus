/**
 * Blocks.java
 */

import java.lang.Integer;
import java.lang.System;

public class Blocks {

    public Blocks(int A) {
        System.out.println(A);
    }


    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        Blocks blocks = new Blocks(x);
        //System.out.println(blocks.f());
    }
}
