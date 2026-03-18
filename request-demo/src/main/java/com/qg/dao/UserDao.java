package com.qg.dao;

import com.qg.pojo.User;
import com.qg.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    //通过用户名和密码查询用户
    public User select(String name, String password) {
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from tb_user where username = ? and password = ?";
            ps = DBUtil.getPs(conn, sql);
            ps.setString(1, name);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return user;
    }
}
