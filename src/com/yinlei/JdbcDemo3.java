package com.yinlei;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * ��ȡ���Ӷ�������ַ�ʽ
 * 
 * @author wuyin
 *
 */
public class JdbcDemo3 {

	public static void main(String[] args) throws Exception {
		// 1��ע������
		Class.forName("com.mysql.jdbc.Driver");
		// 2������һ�����Ӷ���
		//Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
		
		/**
		 * ��һ�����ӷ�ʽ
		 */
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password=root");
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
