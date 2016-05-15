package com.yinlei.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.yinlei.utils.JdbcUtils;

/**
 * 演示JDBC调用存储过程
 * 
 * @author wuyin
 *
 */
public class ProcedureTest {

	/**
	 * 测试不带返回值的存储过程
	 * 
	 * @throws SQLException
	 */
	public static void testPro() throws SQLException {
		// 获得连接对象
		Connection conn = JdbcUtils.getConnection();

		// 创建一个执行存储过程的命令对象
		CallableStatement cstms = conn.prepareCall("{call pro4(?,?)}");

		// 指定"?"的值
		cstms.setInt(1, 1);
		// 指定第二个问号是输出参数
		cstms.registerOutParameter(2, Types.VARCHAR);
		// 执行存储过程
		boolean i = cstms.execute();
		// 获取返回值
		String name = cstms.getString(2);
		System.out.println("接收到的参数是：" + name);

		System.out.println(i); // true
		// 释放资源
		JdbcUtils.release(null, cstms, conn);

	}

	/**
	 * 测试带返回值的存储过程
	 * 
	 * @throws SQLException
	 */
	public static void testPro1() throws SQLException {
		// 获得连接对象
		Connection conn = JdbcUtils.getConnection();

		// 创建一个执行存储过程的命令对象
		CallableStatement cstms = conn.prepareCall("{call pro1(?)}");

		// 指定"?"的值
		cstms.setInt(1, 1);

		// 执行存储过程
		boolean i = cstms.execute();
		System.out.println(i); // true
		// 释放资源
		JdbcUtils.release(null, cstms, conn);

	}

	public static void main(String[] args) {
		try {
			testPro();
			testPro1();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
