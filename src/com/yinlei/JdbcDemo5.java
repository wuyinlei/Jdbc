package com.yinlei;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 演示结果集的移动
 * 
 * @author wuyin
 *
 */
public class JdbcDemo5 {
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
		/*while (rs.next()) {
			System.out.println("id :" + rs.getInt("id") + " name :" + rs.getString("name"));
		}*/
		//第一条数据
		rs.first();
		System.out.println("id :" + rs.getInt("id") + " name :" + rs.getString("name"));
		
		//最后一条数据
		rs.last();
		System.out.println("id :" + rs.getInt("id") + " name :" + rs.getString("name"));
		
		//到指定的行  移动到第四条
		rs.absolute(4);
		System.out.println("id :" + rs.getInt("id") + " name :" + rs.getString("name"));
		
		//移动到第三条
		rs.previous();
		System.out.println("id :" + rs.getInt("id") + " name :" + rs.getString("name"));
		
		//报错    最后一条的下一条数据     
		//rs.afterLast();
		System.out.println("id :" + rs.getInt("id") + " name :" + rs.getString("name"));
		
		// 6、关闭链接、命令对象、结果集
		rs.close();
		stmt.close();
		conn.close();

	}
}
