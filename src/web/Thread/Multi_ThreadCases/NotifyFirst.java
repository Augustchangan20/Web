package web.Thread.Multi_ThreadCases;

/**
 * notify 没有状态保存的
 * notify 早于 wait 之前，什么都唤不醒
 * notify 只能唤醒当前时刻上已经wait的线程，无法唤醒未来wait的线程
 */
public class NotifyFirst {
    static class SubThread extends Thread {
        @Override
        public void run() {
            try {
                synchronized (this) {
                    // this.wait();
                    wait();
                    System.out.println("子线程被唤醒");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // s 明明是一个局部变量，但还是共享的
        SubThread s = new SubThread();

        // 先调用 notify，然后再让子线程运行，调用wait
        // s 指向的对象，就是子线程代码中的 this 指向的对象
        synchronized (s) {
            s.notify();
        }

        s.start();
    }
}
