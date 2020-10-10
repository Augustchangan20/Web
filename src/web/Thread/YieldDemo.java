package web.Thread;

/**
 * yield的使用
 * 两个线程同时打印
 */
public class YieldDemo {
    static class A extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("A");
            }
        }
    }

    static class B extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("B");
                    Thread.yield();
                //Thread. yield()只是放弃了CPU， 但没有放弃抢CPU的资格
                //状态由Running --Ready
                //适用于自己在代码中执行了很长时间了，可以主动放弃CPU看看，让出点时间出来--大公无私
            }
        }
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.start();
        b.start();
    }
}
