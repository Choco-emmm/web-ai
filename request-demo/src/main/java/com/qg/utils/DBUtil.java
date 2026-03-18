package com.qg.utils;


import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static DataSource ds = null;

    static {
        try {
            InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("hikari.properties");
            Properties prop = new Properties();
            prop.load(is);
            HikariDataSource hds = new HikariDataSource();
            hds.setMaximumPoolSize(Integer.parseInt(prop.getProperty("maximumPoolSize", "10")));
            hds.setJdbcUrl(prop.getProperty("jdbcUrl"));
            hds.setUsername(prop.getProperty("username"));
            hds.setPassword(prop.getProperty("password"));
            hds.setDriverClassName(prop.getProperty("driverClassName"));
            ds = hds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取数据库连接（每个事务获取一次）
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    //获取pstmt对象
    public static PreparedStatement getPs(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    //关闭连接（非查询版）
    public static void close(Connection conn, PreparedStatement ps) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //关闭连接（查询版）
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //关闭连接（事务管理版）
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //关闭pstmt对象
    public static void close(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //关闭pstmt对象和结果集
    public static void close(PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
