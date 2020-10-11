package web.Thread.Multi_ThreadCases;

public class SingletonLazyTwice {
    private final String name;
    private final int age;
    private final String gender;

    private static volatile SingletonLazyTwice instance = null;

    // 以下写法是线程安全的么？
    // 不是
    // 这把锁加的毫无意义
    public static SingletonLazyTwice getInstanceUnsafe(){
        if (instance == null){
            synchronized (SingletonLazyTwice.class){
                instance = new SingletonLazyTwice();
            }
        }
        return instance;
    }

    //通过二次判断，既保证了线程安全，又减少了抢锁的次数

    //不过这个 版本任然存在一个小错误

    public static SingletonLazyTwice getInstanceWrong(){
        if (instance == null){
            synchronized (SingletonLazyTwice.class){
                //instance 可能是null，也可能不是null
                if (instance == null){
                    //这里因为锁的存在，只会执行一次
                    //保证了单例
                    instance = new SingletonLazyTwice();
                }else {
                    //抢锁之前，instance是null
                    //但是从抢锁到抢锁成功这段时间
                    //instance已经不是null
                    //代表已经被之前抢到锁的线程实例化好了
                    //也就什么也不需要做了
                }
            }
        }
        return instance;
    }

    //二次判断是为了解决初始化过程竞争的问题
    //volatile为了解决重排序的问题，
    // 二者结合，使得整个单例变成以下形态
    public static SingletonLazyTwice getInstance(){
        if (instance == null){
            // instance == null，然后抢锁
            // 如果 instance != null，需要抢锁么？   不需要
            // 一个线程持有锁，能干扰另一个不抢锁的线程么？  --  不干扰
            //两个线程同步，条件是它们都得去抢锁，并且抢同一把锁，
            // 而这个时候一个线程抢锁，一个不抢锁，所以不干扰
            synchronized (SingletonLazyTwice.class){
                if (instance == null){
                    //下面的代码可能会被重排序
                    //错误的根源在于重排序了，加上volatile就可以修复了

                    instance = new SingletonLazyTwice();
                }
            }
        }
        return instance;
    }

    private SingletonLazyTwice(){
        name = "xiaosong";
        age = 18;
        gender = "男";
    }
}
