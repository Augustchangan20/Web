package web.Thread.Multi_ThreadCases.BlockingQueue;

/**
 * 阻塞队列
 * 实现一个基于数组的队列--循环队列
 */
public class MyBlockingQueue {
    private int[] array = new int[1];
    private volatile int size = 0;
    private int frontIndex = 0;   //指向队首元素的下标
    private int rearIndex = 0;    //指向下一个可用位置的下标

    public synchronized void push(int element) throws InterruptedException {
        //判断队列是否已满
        if (size >= array.length){
            //throw new RuntimeException("sorry，队列已满!");
            wait();     // 等着调用 pop 的线程唤醒，所以在 pop 中实现

        }
        array[rearIndex] = element;
        size++;  //破坏了原子性


        /*rearIndex = rearIndex + 1;
        if (rearIndex >= array.length){
            rearIndex -= array.length;
        }*/
        //两种写法都可以用
        rearIndex = (rearIndex + 1)% array.length;
        notifyAll();   // 唤醒的是调用 pop 时阻塞的线程
    }

    public synchronized int pop() throws InterruptedException {
        if (size == 0){
            //throw new RuntimeException("队列已空");
            wait();     // 等待着调用 push 的线程唤醒
        }
        int element = array[frontIndex];
        frontIndex = (frontIndex + 1)% array.length;
        size--; //破坏了原子性
        // 队列中一定出现空间了
        notifyAll();   // 唤醒调用 push 时阻塞的线程
        return element;
    }
    public int size(){
        return size;//可能有内存可见性问题，用volatile即可
        //加锁是有成本的，且加锁的成本高
        //对于破坏了原子性的，通过synchronized加锁来保证线程安全
        //对于单纯的内存可见性问题，用volatile
    }
}
