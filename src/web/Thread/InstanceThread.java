package web.Thread;

/**
 * 如何实例化一个线程对象
 */
public class InstanceThread {
    static class A extends Thread{
        @Override
        public void run() {
            System.out.println("我是A类");
        }
    }

    static class B implements Runnable{
        @Override
        public void run() {
            System.out.println("我是B类");
        }
    }

    public static void main(String[] args) {
        Thread thread;
        {
            //1.直接new A类的对象，本身就是一个Thread对象
            A a = new A();
            thread = a;
            thread.start();
        }
        {
            //2.new B 类的对象，是一个Runnable接口，作为任务传递给线程对象
            B b = new B();
            //thread = b;    错误，thread是一个线程类，b只是一个普通类，实现了runnable接口
            thread = new Thread(b);
            thread.start();
        }
        {
            //3.new A类的对象，但把该对象单座Runnable使用
            //因为Thread 本来就实现了 Runnable
            A a = new A();
            thread = new Thread(a);
            thread.start();
        }
    }

}
