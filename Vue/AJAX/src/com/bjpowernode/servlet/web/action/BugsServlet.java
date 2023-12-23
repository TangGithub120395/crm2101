package com.bjpowernode.servlet.web.action;

import com.bjpowernode.servlet.utils.DBUtil;
import com.bjpowernode.servlet.web.bean.Bug;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({
        "/bug/list",
})
public class BugsServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String servletPath = req.getServletPath();
        if ("/bug/list".equals(servletPath)){
            doList(req,resp);
        }
    }

    private void doList(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html;charset=utf-8");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        PrintWriter out = resp.getWriter();
        List<Bug> bugList = new ArrayList<>();
        StringBuffer str = new StringBuffer();

        try {
            //获取连接
            conn = DBUtil.getConnection();
            //获取预编译的数据库对象
            String sql = "select * from t_bug";
            ps = conn.prepareStatement(sql);
            //执行sql语句
            rs = ps.executeQuery();
            //处理查询结果集
            while (rs.next()){
                Integer id = rs.getInt("id");
                String detail = rs.getString("detail");
                bugList.add(new Bug(id,detail));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        str.append("[");
        for(Bug bug:bugList){
            str.append("{");
            str.append("\"id\"");
            str.append(":");
            str.append(bug.getId());
            str.append(",");
            str.append("\"detail\"");
            str.append(":");
            str.append("\""+bug.getDetail()+"\"");
            str.append("}");
            str.append(",");
        }
        str.deleteCharAt(str.lastIndexOf(","));
        str.append("]");
        out.println(str);
    }

}
