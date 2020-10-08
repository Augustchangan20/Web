package web.Thread;

/**
 * 我们加入就绪队列的时机是确定的，但什么时候被调度到CPU不确定(随机)，什么时候被从CPU上调度下来不确定(随机)
 * 数据越大，乱序可能性越大
 * 乱序中保持一定公平性，先就绪的先被调度
 */
public class WhoFirst {
    static class PrintA extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                System.out.println("A");
            }
        }
    }

    static class PrintB extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("B");
            }
        }
    }

    public static void main(String[] args) {
        PrintA a = new PrintA();
        PrintB b = new PrintB();
        a.start();
        b.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("main");
        }
    }
}
