package SQL.blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//负责用户登录功能
public class UserLoginAction implements Action {
    @Override
    public void run() {


        System.out.println("用户登录中...");
        System.out.println();

        //读取用户输入的信息
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入用户名>");
        String username = scanner.nextLine();

        System.out.print("请输入密码>");
        String password = scanner.nextLine();

        try(Connection c = DBUtil.getConnection()){
            String sql = "select id,nickname from users where username = ? and password = ?";
            try(PreparedStatement s = c.prepareStatement(sql)){
                s.setString(1,username);
                s.setString(2,password);
                try(ResultSet rs = s.executeQuery()){
                    //username 是 unique的
                    //所以查找的结果，要不返回1行数据（找到了唯一的），要不返回0行数据（没找到）
                    //不可能有多行数据
                    if(rs.next()){
                        //  .next  的意思是让结果集走向下一行，如果下一行有有效数据，返回true,否则返回false
                        int id = rs.getInt(1);
                        String nickname = rs.getString(2);
                        User user = new User();
                        user.id = id;
                        user.username = username;
                        user.nickname = nickname;
                        //进行登录
                        User.login(user);

                    }else {
                        System.out.println("***用户名或者密码错误，请重新输入！");

                    }
                }

            }

        }catch (SQLException e){
            System.out.println("错误： " +e.getMessage());
        }
    }
}
