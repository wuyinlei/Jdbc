package com.yinlei.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.yinlei.utils.JdbcUtils;

//演示为什么需要事务过程，自己去写错误
public class TransactionTest {

	// 演示为什么需要事务过程，自己去写错误
	public static void test() {

		PreparedStatement pstms = null;
		PreparedStatement pstms2 = null;
		try {
			// 获得连接对象
			Connection conn = JdbcUtils.getConnection();
			String sql = "update blank set money = money -?where id=?";
			pstms = conn.prepareStatement(sql);
			pstms.setFloat(1, 5000);
			pstms.setInt(2, 1);
			int n = pstms.executeUpdate();
			System.out.println("张无忌：" + n);

			System.out.println(10 / 0); // 第一条执行成功，但是第二条数据执行不了

			String sql1 = "update blank set money = money +?where id=?";
			pstms2 = conn.prepareStatement(sql1);
			pstms2.setFloat(1, 5000);
			pstms2.setInt(2, 2);
			int i = pstms2.executeUpdate();
			System.out.println("小忌：" + i);
			

			JdbcUtils.release(null, pstms2, conn);
			JdbcUtils.release(null, pstms, conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 采用事务转账失败的情况
	 * 
	 * @throws SQLException
	 */
	public static void test1() throws SQLException {
		// 获得连接对象
		Connection conn = JdbcUtils.getConnection();
		PreparedStatement pstms = null;
		PreparedStatement pstms2 = null;
		// 让数据库不要自动提交sql语句
		conn.setAutoCommit(false); // 默认true
		try {
			String sql = "update blank set money = money -?where id=?";
			 pstms = conn.prepareStatement(sql);
			pstms.setFloat(1, 1000);
			pstms.setInt(2, 1);
			int n = pstms.executeUpdate();
			System.out.println("张无忌：" + n);

			//System.out.println(10 / 0); // 第一条执行成功，但是第二条数据执行不了,会出现异常

			String sql1 = "update blank set money = money +?where id=?";
			pstms2 = conn.prepareStatement(sql1);
			pstms2.setFloat(1, 1000);
			pstms2.setInt(2, 2);
			int i = pstms2.executeUpdate();
			System.out.println("小忌：" + i);

			//手动提交
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback(); // 事务回滚，如果是出现异常，就回滚
		}
		JdbcUtils.release(null, pstms2, conn);
		JdbcUtils.release(null, pstms, conn);

	}

	public static void main(String[] args) {
		//test();
		try {
			test1();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
