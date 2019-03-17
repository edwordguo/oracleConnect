package com.huawei.utils;

/**
 * @author rango
 * @date 2019/3/11 22:20
 */
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
/*
    操作JDBC的工具类
 */
public class JDBCUtils {
    //1. 构造方法私有.
    private JDBCUtils() {
    }

    //2. 定义成员变量, 记录配置文件的信息.
    private static String driverClass;
    private static String url;
    private static String username;
    private static String password;

    //3. 定义方法, 读取配置文件的信息, 并且赋值给变量.
    public static void readConfig() {
        try {
        Properties pp = new Properties();
//            pp.load(new FileReader("jdbc.properties"));
            //String path = JDBCUtils.class.getClassLoader().getResource("jdbc.properties").getPath();
            InputStream is = Thread.currentThread().getClass().getResourceAsStream("/jdbc.properties");
//            System.out.println(path);
            //FileInputStream in = new FileInputStream("路径是"+path);
            pp.load(is);
            //prop.getProperty("username");
            driverClass = pp.getProperty("driverClass");
            url = pp.getProperty("url");
            username = pp.getProperty("username");
            password = pp.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //4. 在静态代码块中, 完成注册驱动的动作.
    static {
        try {
            //调用读取配置文件的方法.
            readConfig();

            Class.forName(driverClass);
            System.out.println("注册驱动");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //5. 对我提供一个方法, 用来获取连接对象.
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //6. 释放资源.
    public static void release(Connection conn, Statement stat, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                    stat = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                        conn = null;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void release(Connection conn, Statement stat) {
        try {
            if (stat != null) {
                stat.close();
                stat = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

