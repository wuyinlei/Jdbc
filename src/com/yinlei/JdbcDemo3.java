package com.yinlei;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * ��ȡ���Ӷ�������ַ�ʽ
 * 
 * @author wuyin
 *
 */
public class JdbcDemo3 {

	public static void main(String[] args) throws Exception {

		// ���ӵ�ַ
		String url = "jdbc:mysql://localhost:3306/mydb";
		// �û���
		String name = "root";
		// ����
		String pwd = "root";

		Properties pro = new Properties();
		InputStream is = JdbcDemo3.class.getClassLoader().getResourceAsStream("dbcof.properties");
		pro.load(is);
		// Connection conn =
		// DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", pro);
		url = pro.getProperty("url");
		name = pro.getProperty("user");
		pwd = pro.getProperty("password");

		// 1��ע������
		Class.forName("com.mysql.jdbc.Driver");
		// 2������һ�����Ӷ���
		// Connection conn =
		// DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",
		// "root", "root");

		/**
		 * ��һ�����ӷ�ʽ �û�����������Ϊ��������ַ����
		 */
		// Connection conn =
		// DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password=root");
		/**
		 * �ڶ��ַ�ʽ ����properties�ļ�
		 */
		// ��ȡproperties�ļ�
		/*
		 * Properties pro = new Properties(); InputStream is =
		 * JdbcDemo3.class.getClassLoader().getResourceAsStream(
		 * "dbcof.properties"); pro.load(is); Connection conn =
		 * DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",pro);
		 */

		/**
		 * �����ַ�ʽ�������Ӷ���
		 */
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
