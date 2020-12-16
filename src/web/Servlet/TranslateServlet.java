package web.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TranslateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String english = req.getParameter("english");
        //TODO:没有任何的错误处理
        String chinese;
        // select chinese from dictionary where english = ?
        //最终查的语句

        try(Connection c = Translate_DBUtil.getConnection()){
            String sql = "select chinese from dictionary where english = ?";
            try(PreparedStatement s = c.prepareStatement(sql)){
                s.setString(1,english);
                try(ResultSet r = s.executeQuery()){
                    //因为创建表的时候，english的字段设定是unique
                    //所以，上述Sql，要不给一行数据，要不返回0行数据
                    if(r.next()){
                        //返回了一行
                        chinese = r.getString("chinese");
                    }else {
                        //没有结果
                        chinese = "抱歉的，不认识呢";
                    }
                }
            }
        }catch (SQLException e){
            throw new ServletException(e);
        }
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>翻译结果</h1>");
        writer.println("<p>"+english+"意思是"+chinese+"</p>");
    }
}
