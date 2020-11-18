package web.MutiThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


public class CallableDemo {
    static class B implements Callable<Long> {
        private final int n;

        B(int n) {
            this.n = n;
        }

        @Override
        public Long call() {
            long r = 0;
            for (int i = 1; i <= n; i++) {
                r += i;
            }

            return r;   // <-- 结果来自这里
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        B b = new B(100_0000);

        FutureTask<Long> futureTask = new FutureTask<>(b);
        Thread thread = new Thread(futureTask);
        thread.start();

        // 这个方法会一直阻塞到 B 线程把计算完成
        long r = futureTask.get();

        // r 就是 B 线程的计算结果
    }
}