package web.ThreadSafetyProblem;
/**
 * 把握两个点
 * 1.都加锁
 * 2.同一个对象
 */
public class FixUnSafeDemo {
    private static final long N = 1_0000_0000L;
    private static long v = 0;

    static class Add extends Thread{
        @Override
        public void run() {
            synchronized (Sub.class){
            //synchronized (Sub.currentThread()){//不可以
            for (long i = 0; i < N ; i++) {
                //synchronized(FixUnSafeDemo.class){//可以，多线程转为单线程，速度慢
                    v++;
                }
            }
        }
    }
    static class Sub extends Thread{
        @Override
        public void run() {
            synchronized (Sub.class){
            //synchronized (Sub.currentThread()){ //不可以，本质还是Thread.CurrentThread,还是两个线程
            for (long i = 0; i < N ; i++) {
                //synchronized (FixUnSafeDemo.class){
                    v--;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Add a = new Add();
        Sub s = new Sub();
        a.start();
        s.start();

        //为什么这里需要join -----等待两个线程都结束
        a.join();
        s.join();

        //修复以上代码，保证这里线程 安全，打印的值为0
        System.out.println(v);
    }

}
