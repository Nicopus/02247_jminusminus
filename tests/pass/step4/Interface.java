/**
 * Interface.java
 */

interface A extends B {
    public int f(int x);
}

interface B {
    public int e(int x);
}

class C implements A {
    public int f(int x) {
        return x * x;
    }
}

class D implements A {
    public int f(int x) {
        return x * x * x;
    }
}

public class Interface {
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        B b = new B();
        C c = new C();
        System.out.println(b.f(x));
        System.out.println(c.f(x));
    }
}
