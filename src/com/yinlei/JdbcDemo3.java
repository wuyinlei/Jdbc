package com.yinlei;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * 获取连接对象的三种方式
 * 
 * @author wuyin
 *
 */
public class JdbcDemo3 {

	public static void main(String[] args) throws Exception {

		// 连接地址
		String url = "jdbc:mysql://localhost:3306/mydb";
		// 用户名
		String name = "root";
		// 密码
		String pwd = "root";

		Properties pro = new Properties();
		InputStream is = JdbcDemo3.class.getClassLoader().getResourceAsStream("dbcof.properties");
		pro.load(is);
		// Connection conn =
		// DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", pro);
		url = pro.getProperty("url");
		name = pro.getProperty("user");
		pwd = pro.getProperty("password");

		// 1、注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		// 2、创建一个连接对象
		// Connection conn =
		// DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",
		// "root", "root");

		/**
		 * 第一种连接方式 用户名和密码作为参数在网址后面
		 */
		// Connection conn =
		// DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password=root");
		/**
		 * 第二种方式 采用properties文件
		 */
		// 读取properties文件
		/*
		 * Properties pro = new Properties(); InputStream is =
		 * JdbcDemo3.class.getClassLoader().getResourceAsStream(
		 * "dbcof.properties"); pro.load(is); Connection conn =
		 * DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",pro);
		 */

		/**
		 * 第三种方式创建连接对象
		 */
		Connection conn = DriverManager.getConnection(url, name, pwd);
		// 3、创建一个sql语句发送的命令对象
		Statement stmt = conn.createStatement();

		// 4、执行SQL,拿到查询的结果集对象
		ResultSet rs = stmt.executeQuery("select * from stu");
		// 5、输出结果集的数据
		while (rs.next()) {
			System.out.println("id :" + rs.getInt("id") + " name :" + rs.getString("name"));
		}
		// 6、关闭链接、命令对象、结果集
		rs.close();
		stmt.close();
		conn.close();

	}
}
