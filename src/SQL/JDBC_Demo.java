package SQL;

import java.sql.*;

public class JDBC_Demo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //注册驱动--选择乙方
        Class.forName("com.mysql.jdbc.Driver");
        /**
         * 完整的目标是执行 select*from student_0929;
         */
         //建立数据库连接
        //写明MySQL服务器所在地
        //哟吼写项目，只需要 修改默认数据库名称即可
        String defaultDatabaseName = "song_0927";
        //写MySQL密码
        String password = "root";
        //下面这里，基本不变
        String user = "root";
        String url = "jdbc:mysql://127.0.0.1:3306/" + defaultDatabaseName + "?useSSL=false&characterEncoding=utf8";

        Connection connection = DriverManager.getConnection(url,user,password);
        //打印 connection 对象，验证是否连接成功
        System.out.println(connection);
        //queryDemo(connection);
        
        updateDemo(connection);


        //-1:关闭connection连接
        connection.close();
    }

    private static void updateDemo(Connection connection) throws SQLException {
        //获取Statement对象
        Statement statement = connection.createStatement();
        String sql = "insert into student_0929(sn,name,sex) values ('20201001','xiaoliu','男')";
        int affectedRows = statement.executeUpdate(sql);
        System.out.printf("Query OK,%d row affected%n",affectedRows);
        statement.close();
    }


    private static void queryDemo(Connection connection) throws SQLException {
        //要真正的执行sql语句，并且获取数据库返回的结果
        //Statement 代表的是" 语句" 的抽象对象
        Statement statement = connection.createStatement();
        String sql = "select*from student_0929";//不在要求必须以分号结尾了
        //1.executeQuery 用在查询（Query）场景下 --查询就要有结果
        //2.ResultSet 代表是结果集 的抽象对象
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            String id = resultSet.getString(1);
            String sn = resultSet.getString(2);
            String name = resultSet.getString(3);
            String sex = resultSet.getString(4);

        }

        //-3:关闭 resultSet 对象
        resultSet.close();
        //-2:关闭statement对象
        statement.close();
    }
}
