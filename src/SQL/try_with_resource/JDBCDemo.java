package SQL.try_with_resource;

import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //直接写死
        String url = "jdbc:mysql://127.0.0.1:3306/song_0927?useSSL=false&characterEncoding=utf8";
        String user = "root";
        String password = "root";
        //固定流程----注册驱动
        Class.forName("com.mysql.jdbc.Driver");


        //不是用try_with_source的写法，传统写法
        {
            Connection connection = DriverManager.getConnection(url, user, password);
            //执行具体的操作
            connection.close();
        }

        //使用try_with_source的写法，jdk1.8之后支持的，目的为了简化代码
        {
            try(Connection connection = DriverManager.getConnection(url,user,password)){
                //执行具体操作
                try(Statement statement = connection.createStatement()){
                    String sql = "show tables";
                    try(ResultSet resultSet = statement.executeQuery(sql)){
                        while (resultSet.next()){
                            System.out.println(resultSet.getString(1));

                        }
                    }
                }
            }
            //这里会自动调用connection.close()操作，编译器会自动帮你加上相应的代码
        }



    }
}
