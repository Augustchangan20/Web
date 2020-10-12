package web.Thread.Multi_ThreadCases.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 *一个接口三个类
 * Java中的阻塞队列
 */
public class JavaBlockingQueueMain {
    static BlockingQueue<Integer> q1 = new ArrayBlockingQueue<>(16);//自带容量
    static BlockingQueue<Integer> q2 = new LinkedBlockingQueue<>();//不带容量
    static BlockingQueue<Integer> q3 = new PriorityBlockingQueue<>();//提供优先级

    static class Producer extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; true; i++) {
                    q1.put(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Producer().start();
        }

        while (true) {
            Integer take = q1.take();
            System.out.println(take);
        }
    }
}
