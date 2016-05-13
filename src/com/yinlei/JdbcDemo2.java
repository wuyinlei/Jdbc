package com.yinlei;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

/**
 * 三种方式注册驱动
 * 
 * @author wuyin
 *
 */
public class JdbcDemo2 {

	public static void main(String[] args) throws Exception {
		// 连接地址
		String url = "jdbc:mysql://localhost:3306/mydb";
		// 用户名
		String name = "root";
		// 密码
		String pwd = "root";
		// 1、注册驱动
		// Class.forName("com.mysql.jdbc.Driver");
		/**
		 * 第一种注册方式
		 */
		// Driver driver = new Driver();
		// DriverManager.registerDriver(driver);

		/**
		 * 第二种方式 反射机制
		 */
		// Driver driver = (Driver)
		// Class.forName("com.mysql.jdbc.Driver").newInstance();
		// DriverManager.registerDriver(driver);

		/**
		 * 第三种注册方式
		 */
		Class.forName("com.mysql.jdbc.Driver");

		// 2、创建一个连接对象
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
