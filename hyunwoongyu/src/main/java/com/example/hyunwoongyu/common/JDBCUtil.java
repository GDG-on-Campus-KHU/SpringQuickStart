package com.example.hyunwoongyu.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {

    /**
     * Connection 객체를 얻어오는 메서드
     * @return Connection 객체
     */
    public static Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");
            // 커넥션 연결 정보를 이용하여 Connection 객체 얻기
            return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test",
                    "test", "test");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(PreparedStatement stmt, Connection conn) {
        if (stmt != null) {
            try {
                if (!stmt.isClosed()) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                stmt = null;
            }
        }
        
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conn = null;
            }
        }
    }

    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
        if (rs != null) {
            try {
                if (!rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                rs = null;
            }
        }
        if (stmt != null) {
            try {
                if (!stmt.isClosed()) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                stmt = null;
            }
        }        
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conn = null;
            }
        }
    }
}