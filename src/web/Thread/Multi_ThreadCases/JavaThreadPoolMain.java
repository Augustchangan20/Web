package web.Thread.Multi_ThreadCases;

import java.util.concurrent.*;

/**
 * jdk1.8 中提供的线程池
 * 为什么需要线程工厂  --因为职责被分离了
 * 创建线程的过程在线程池代码内部 ，我们无法控制，比如我们想改个名字之类的，没有机会
 * 所以线程提供了一个工厂，供我们使用，创建线程的时候有机会调用我们的方法
 */
public class JavaThreadPoolMain {
    static class MyThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "我随便起的名字");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);

        // 创建线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                10,
                10,
                0,
                TimeUnit.SECONDS,
                queue,
                new MyThreadFactory(),
                //new ThreadPoolExecutor.AbortPolicy()   //默认抛出异常Always throws RejectedExecutionException.
                //new ThreadPoolExecutor.DiscardPolicy() //丢掉现在的任务
                //new ThreadPoolExecutor.DiscardOldestPolicy() //丢掉队列中最老的
               new ThreadPoolExecutor.CallerRunsPolicy()   //哪个线程调用excute,哪个线程就去运行任务

        );

        for (int i = 0; true; i++) {
            // 创建让线程池执行的任务
            Runnable target = new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            // 等同于把任务提交给线程池
            // 线程池内部会选择一个空闲的线程去执行该任务
            System.out.println("提交第 " + i + " 个任务");
            System.out.println(queue.size());
            threadPool.execute(target);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
