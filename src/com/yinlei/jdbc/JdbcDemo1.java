package com.yinlei.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 演示第一个jdbc案例
 * 
 * @author wuyin
 *
 */
public class JdbcDemo1 {

	public static void main(String[] args) throws Exception {
		// 1、注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		// 2、创建一个连接对象
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
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
