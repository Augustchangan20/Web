package web.Thread.Multi_ThreadCases;
import java.util.Scanner;

/**
 * Wait 和 Notify 演示
 */
public class WaitNotifyDemo {
    private static final Object o = new Object();

    // 两个线程，子线程打印 -> wait 等待唤醒 -> 继续打印
    static class SubThread extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("等待之前");
                synchronized (o) {
                    o.wait();
                }
                System.out.println("等待之后");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 通过 scanner 等用户输入，用户输入任意字符，就唤醒子线程
    public static void main(String[] args) {
        SubThread thread = new SubThread();
        thread.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();     // 至少一段时间之后
        synchronized (o) {
            o.notifyAll();
            // o.notify();
        }
    }

    /*
    public static void main(String[] args) throws InterruptedException {
        Object p = new Object();
        Object q = new Object();

        // p.wait();    会抛异常

        // 也会抛异常
//        synchronized (q) {
//            p.wait();
//        }

        synchronized (p) {
            p.wait();
        }
    }
    */
}
