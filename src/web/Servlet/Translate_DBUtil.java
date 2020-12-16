package web.Servlet;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 使用懒汉单例 - Double Check
 * URL 的进一步理解
 */
public class Translate_DBUtil {
    private static volatile DataSource dataSource = null;
    public static Connection getConnection() throws SQLException{
        if (dataSource == null){
            synchronized (Translate_DBUtil.class){
                if (dataSource == null){
                    dataSource = initDataSource();
                }
            }
        }
        return dataSource.getConnection();
    }

    private static DataSource initDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/song_dictionary?useSSL=false&characterEncoding=utf8");
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("root");
        return mysqlDataSource;
    }
}
