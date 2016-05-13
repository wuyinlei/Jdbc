package com.yinlei;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.w3c.dom.css.RGBColor;

/**
 * 
 * 曾删改查
 * 
 * @author wuyin
 *
 */
public class JdbcDemo4 {

	public static void main(String[] args) throws Exception {
		// 1、注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		// 2、创建一个连接对象
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
		// 3、创建一个sql语句发送的命令对象
		Statement stmt = conn.createStatement();

		// 4、执行SQL,拿到查询的结果集对象
		// String sql = "insert into stu(id,name,sex,age,address)
		// values(12,'小白','男',20,'河南省桃花岛')"; 增
		// String sql = "UPDATE stu SET sex ='男' WHERE id = 12"; 改
		// String sql = "DELETE from stu where id = 12";
		// int i = stmt.executeUpdate(sql);
		// System.out.println("改变的条目：" + i);
		//String sql = "select id 编码,name 姓名  from stu";
		String sql = "select * from stu";

		ResultSet rs = stmt.executeQuery(sql);
		// 5、输出结果集的数据

		while (rs.next()) {
			// System.out.println("id :" + rs.getInt("id") + " name :" +
			// rs.getString("name"));
			// 第一种根据字段名查找
			// int id = rs.getInt("编码"); //
			// String name = rs.getString("姓名");
			// int age = rs.getInt("age");
			// String addr = rs.getString("address");
			// System.out.println("id :" + id + " name :" + name);
			// System.out.println("id :" + id + " name :" + name + " age :" +
			// age + " addr " + addr);

			// 第二种是根据字段的索引 是结果集的序号(下标从 1 开始)
			// int id = rs.getInt(1);
			// String name = rs.getString(2);
			// System.out.println("id :" + id + " name :" + name);
			
			
			/**
			 * String sql = "select * from stu";
			 * 当sql语句是上面的时候，就可以下面的这种方式查找了
			 */
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			String addr = rs.getString("address");
			System.out.println("id :" + id + " name :" + name + " age :" + age + " addr " + addr);
		}
		// 6、关闭链接、命令对象、结果集
		rs.close();
		stmt.close();
		conn.close();

	}

}
