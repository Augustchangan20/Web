package SQL.blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class
    //负责用户发表文章功能
ArticlePublishAction implements Action {
    @Override
    public void run() {
        if (!User.isLogined()){
            System.out.println("***请选择登录，才能操作该功能...");
            return;
        }

        //和注册用户基本一致
        //获取用户的输入（标题，正文）
        //根据当前登录用户，获取作者id
        //通过调用API，获取当前时间
        System.out.println("正在发表文章中...");
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入文章标题>");
        String title = scanner.nextLine();

        System.out.print("请输入文章正文>");
        String content = scanner.nextLine();
        int authorId = User.getCurrentUser().id;
        Date publishedAt = new Date();//new 完的对象，本来就是当前时间
        //publishedAt 现在是Date对象，把Date 对象format 成 String 格式
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String publishedAtStr = format.format(publishedAt);

        //信息获取完毕，通过JDBC执行 insert 操作
        try(Connection c = DBUtil.getConnection()){

            String sql = "insert into articles (author_id,title,published_at,content) values(?,?,?,?)";
            try(PreparedStatement s = c.prepareStatement(sql)){
                s.setInt(1,authorId);
                s.setString(2,title);
                s.setString(3,publishedAtStr);//2020-10-03 20:10:52
                s.setString(4,content);

                s.executeUpdate();
                System.out.println("《"+ title+"》 文章发表成功！");

            }


        }catch (SQLException e){
            System.out.println("错误： "+e.getMessage());
        }

    }
}
