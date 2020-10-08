package web.Thread;

/**
 * anonymous 匿名
 * 通过匿名类创建线程（Thread）
 * 省去创建类的过程
 *
 * 现象;  各自占用时间片在运行
 */
public class Anonymous {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true){
                System.out.println("我是子线程");
            }
        });
        t.start();
        while (true){
            System.out.println("我是主（main）线程");
        }
    }
}
