package web.Thread;

/**
 * 打印线程的所有状态
 */
public class PrintAllThreadState {
    public static void main(String[] args) {
        Thread.State[] values = Thread.State.values();
        for (Thread.State s : values) {
            System.out.println(s);
        }
    }
}
