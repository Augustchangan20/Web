package web.Servlet; /**
 * 我的第一个的动态资源
 * 能导出HttpServlet这个类，说明jar包导入成功
 */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFirstDynamicResource extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 通过 query string，取到 wd 这个 name 所对应的 value
        String wd = req.getParameter("wd");

        // 2. 返回的 Content-Type 是 text/html; 字符集编码是 utf-8
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();

        // 3. 输出的响应的内容
        writer.println("<h1>我是 修改后的代码</h1>");
        writer.println("<p>用户要查找的关键字是: " + wd + "</p>");
    }
}

