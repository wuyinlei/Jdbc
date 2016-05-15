package com.yinlei.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

//ͨ�õ��������ݿ����
public class JdbcUtils {

	private static String driverClass = "";
	private static String url = "";
	private static String user = "";
	private static String password = "";

	// �ڹ��췽��֮ǰ����
	static {
		ResourceBundle rb = ResourceBundle.getBundle("dbcof");
		driverClass = rb.getString("driverClass");
		url = rb.getString("url");
		user = rb.getString("user");
		password = rb.getString("password");

		try {
			// ע������
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �ͷ���Դ
	 * 
	 * @param rs
	 *            �����
	 * @param stmt
	 *            ��ѯ
	 * @param conn
	 *            ����
	 */
	public static void release(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
