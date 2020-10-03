package SQL.try_with_resource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.lang.invoke.CallSite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class use_DataSource {
    public static void getConnectionUseDriverManger() throws ClassNotFoundException, SQLException {
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/song_0927?useSSL=false&characterEncoding=utf8";
        String user = "root";
        String password = "root";
        try(Connection connection = DriverManager.getConnection(url,user,password)){
            //目的就是获取Connection对象

        }
    }


    //1.这是新版JDBC标准提供的写法
    //2.写法你url的方式更加明确，不容易出现拼写错误
    //3.支持连接池的方式，所以可能 效率更高
    public static void getConnectionUseDataSoruce() throws SQLException {
        DataSource dataSource;
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setServerName("127.0.0.1");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("root");
        mysqlDataSource.setDatabaseName("song_0927");
        mysqlDataSource.setUseSSL(false);
        mysqlDataSource.setCharacterEncoding("utf8");

        //DataSource 也支持url的方式指定连接参数
        //mysqlDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/song_0927?useSSL=false&characterEncoding=utf8");

        dataSource = mysqlDataSource;
        try(Connection connection = dataSource.getConnection()){
            //目的就是获取Connection对象

        }
    }

}
