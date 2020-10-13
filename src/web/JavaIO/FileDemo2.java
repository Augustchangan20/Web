package web.JavaIO;

import java.io.File;
import java.util.Scanner;

/**
 * 文件重命名
 */
public class FileDemo2 {
    public static void main(String[] args) {
       // File file = new File("测试目录\\a.txt");
        //模拟文件重命名操作
    /*    File dest = new File("测试目录\\b.txt");
        boolean b = file.renameTo(dest);
        System.out.println(b);*/

    //模拟剪切-粘贴的操作
        /*File dest = new File("a.txt");
        boolean b = file.renameTo(dest);
        System.out.println(b);*/

        //删除操作
 /*       File file = new File("测试目录\\非空文件");
        boolean delete = file.delete();
        System.out.println(delete);*/


     /*   Scanner scanner = new Scanner(System.in);
        {
            File file = new File("测试目录\\a.txt");
            file.delete();
            System.out.println("等待一会，观察a是否被删除");
            scanner.nextLine();
        }
        {
            File file = new File("测试目录\\b.txt");
            file.deleteOnExit();
            System.out.println("等待一会，观察b是否被删除");
            scanner.nextLine();
        }*/

     File file = new File("测试目录\\非空文件");
     File[] files = file.listFiles();
     /*   for (File f:files) {
            System.out.println(f.getAbsolutePath());
        }*/
     String[] list = file.list();
        for (String s:list
             ) {
            System.out.println(s);

        }
    }
}
