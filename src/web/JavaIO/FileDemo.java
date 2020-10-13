package web.JavaIO;

import java.io.File;
import java.io.IOException;

public class FileDemo {
    public static void main(String[] args) {
        // 先使用绝对路径
        // 1. 对应一个实际存在的文件
        // 2. 对应一个实际不存在的文件
        // 3. 对应一个实际存在的目录
        // 4. 修改下文件的属性，观察下代码的打印有什么不同
        //String path = "F:\\javaCode\\测试IO文件\\song.txt";
        // !!!这里为什么是双斜杠？
        //本质上还是单斜杠，另一个代表转义
        String path = "a\\b\\c\\d\\e\\hello.txt";



        // 构建文件对象
        File file = new File(path);     // 路径对应的文件，但文件可能实际上不存在

        // 常见属性
        //文件存在&&是普通文件
        //System.out.println(file.isFile());
        //文件存在&&是文件夹
       // System.out.println(file.isDirectory());
        //是绝对路径吗
        //System.out.println(file.isAbsolute());
        //是隐藏文件吗
        //System.out.println(file.isHidden());
        //文件是否存在
        System.out.println(file.exists());
        //绝对路径
        //System.out.println(file.getAbsolutePath());
        //创建时路径
       // System.out.println(file.getPath());
        //文件名字
        //System.out.println(file.getName());
        //父节点名字
        //System.out.println(file.getParent());
        //是否可读
        //System.out.println(file.canRead());
        //是否可写
       // System.out.println(file.canWrite());
        //是否可以执行
       // System.out.println(file.canExecute());

        // 测试目录2\hello.txt
        // 要创建 hello.txt 文件，要求测试目录2首先存在
        // 但这里，测试目录2不存在，所以会出错
        // 1. 演示创建成功
        // 2. 演示文件已存在
        // 3. 上一级目录都不存在
 /*       try {
            boolean newFile = file.createNewFile();     // 创建普通文件
            System.out.println(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        /*boolean mkdir = file.mkdir();  //创建文件夹
        System.out.println(mkdir);*/

        System.out.println("会把中间没有的目录，循环创建创建出来");
        boolean mkdirs = file.mkdirs();
        System.out.println(mkdirs);
    }
}
