package com.yinlei.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.yinlei.utils.JdbcUtils;

//��ʾΪʲô��Ҫ������̣��Լ�ȥд����
public class TransactionTest {

	// ��ʾΪʲô��Ҫ������̣��Լ�ȥд����
	public static void test() {

		PreparedStatement pstms = null;
		PreparedStatement pstms2 = null;
		try {
			// ������Ӷ���
			Connection conn = JdbcUtils.getConnection();
			String sql = "update blank set money = money -?where id=?";
			pstms = conn.prepareStatement(sql);
			pstms.setFloat(1, 5000);
			pstms.setInt(2, 1);
			int n = pstms.executeUpdate();
			System.out.println("���޼ɣ�" + n);

			System.out.println(10 / 0); // ��һ��ִ�гɹ������ǵڶ�������ִ�в���

			String sql1 = "update blank set money = money +?where id=?";
			pstms2 = conn.prepareStatement(sql1);
			pstms2.setFloat(1, 5000);
			pstms2.setInt(2, 2);
			int i = pstms2.executeUpdate();
			System.out.println("С�ɣ�" + i);
			

			JdbcUtils.release(null, pstms2, conn);
			JdbcUtils.release(null, pstms, conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��������ת��ʧ�ܵ����
	 * 
	 * @throws SQLException
	 */
	public static void test1() throws SQLException {
		// ������Ӷ���
		Connection conn = JdbcUtils.getConnection();
		PreparedStatement pstms = null;
		PreparedStatement pstms2 = null;
		// �����ݿⲻҪ�Զ��ύsql���
		conn.setAutoCommit(false); // Ĭ��true
		try {
			String sql = "update blank set money = money -?where id=?";
			 pstms = conn.prepareStatement(sql);
			pstms.setFloat(1, 1000);
			pstms.setInt(2, 1);
			int n = pstms.executeUpdate();
			System.out.println("���޼ɣ�" + n);

			//System.out.println(10 / 0); // ��һ��ִ�гɹ������ǵڶ�������ִ�в���,������쳣

			String sql1 = "update blank set money = money +?where id=?";
			pstms2 = conn.prepareStatement(sql1);
			pstms2.setFloat(1, 1000);
			pstms2.setInt(2, 2);
			int i = pstms2.executeUpdate();
			System.out.println("С�ɣ�" + i);

			//�ֶ��ύ
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback(); // ����ع�������ǳ����쳣���ͻع�
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
