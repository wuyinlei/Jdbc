package com.yinlei;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * ���ַ�ʽע������
 * 
 * @author wuyin
 *
 */
public class JdbcDemo2 {

	private static String url = "jdbc:mysql://localhost:3306/mydb";
	private static String name = "root";
	private static String pwd = "root";

	public static void main(String[] args) throws Exception {
		// 1��ע������

		// 1��ע������
		Class.forName("com.mysql.jdbc.Driver");
		// 2������һ�����Ӷ���
		Connection conn = DriverManager.getConnection(url, name, pwd);
		// 3������һ��sql��䷢�͵��������
		Statement stmt = conn.createStatement();

		// 4��ִ��SQL,�õ���ѯ�Ľ��������
		ResultSet rs = stmt.executeQuery("select * from stu");
		// 5����������������
		while (rs.next()) {
			System.out.println("id :" + rs.getInt("id") + " name :" + rs.getString("name"));
		}
		// 6���ر����ӡ�������󡢽����
		rs.close();
		stmt.close();
		conn.close();

	}

}
