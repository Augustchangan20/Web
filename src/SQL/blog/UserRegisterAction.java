package SQL.blog;

import java.sql.*;
import java.util.Scanner;

//完整实现用户注册功能
public class UserRegisterAction  implements Action{
    @Override
    public void run() {

        //1.提示用户输入需要的信息，并且使用jdbc执行sql
        System.out.println("用户注册>");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入您的用户名称>");
        String username = scanner.nextLine();
        System.out.print("请输入您的用户昵称>");
        String nickname = scanner.nextLine();
        System.out.print("请输入密码>");
        String password = scanner.nextLine();

        try(Connection connection = DBUtil.getConnection()){
            String sql = "insert into users(username,nickname,password) values(?,?,?)";
            try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                //Statement.RETURN_GENERATED_KEYS 表示返回生成的keys

                statement.setString(1,username);
                statement.setString(2,nickname);
                statement.setString(3,password);

                statement.executeUpdate();
                int id;
                try(ResultSet r = statement.getGeneratedKeys()){
                    r.next();
                     id = r.getInt(1);
                }
                System.out.println("欢迎 "+nickname+"，您已注册成功");

                //这里选择用户成功注册后自动登录
                User user = new User();
                user.id = id;
                user.nickname = nickname;
                user.username = username;
                User.login(user);
            }

        }catch (SQLException e){
            System.out.println("错误; "+e.getMessage());
        }

    }
}
