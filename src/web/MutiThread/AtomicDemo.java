package web.MutiThread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 常用类型的原子类实现：
 *     提供了实现线程安全的替代工具，而且往往效率更高
 */
public class AtomicDemo {
    public static void main(String[] args) {
        int n = 0;

        int r = n++;    // 不是线程安全的
        n += 3; // 不是线程安全的
    }

    public static void v1() {
        int n = 0;

        // 可以实现线程安全，但由于用到了锁，性能较差
        synchronized (AtomicDemo.class) {
            int r = n++;
            n += 3;
        }
    }

    public static void v2() {
        //v2的方法性能更好，以后这种简单的变量处理要保证线程安全，就优先考虑原子类
        AtomicInteger n = new AtomicInteger(0);     // 原子类版本的 n = 0

        // 使用 JDK 提供的原子类操作，性能较高（因为 原子类 内部其实用的是 CAS 进行实现，不需要加锁）
        int r = n.getAndIncrement();                // 原子类版本的 int r = n++;

        n.getAndAdd(3);                       // 原子类版本的 n += 3;
    }
}