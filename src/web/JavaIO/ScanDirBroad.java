package web.JavaIO;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 广度优先遍历
 *    深度优先与栈有关系
 *    广度优先一定用到队列
 */
public class ScanDirBroad {
    public static void main(String[] args) {
        File root = new File("测试目录");
        Queue<File> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            File node = queue.poll();
            if (node.isDirectory()){
                System.out.println(node.getAbsolutePath()+"\\");
            }else if (node.isFile()){
                System.out.println(node.getAbsolutePath());
            }
            //找到Node的所有孩子
            if(node.isDirectory()){
                File[] children = node.listFiles();
                if (children != null){
                    for (File child : children) {
                        queue.offer(child);
                    }
                }
            }
        }
    }
}
