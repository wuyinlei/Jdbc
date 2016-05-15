package com.yinlei.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.yinlei.utils.JdbcUtils;

/**
 * ��ʾJDBC���ô洢����
 * 
 * @author wuyin
 *
 */
public class ProcedureTest {

	/**
	 * ���Բ�������ֵ�Ĵ洢����
	 * 
	 * @throws SQLException
	 */
	public static void testPro() throws SQLException {
		// ������Ӷ���
		Connection conn = JdbcUtils.getConnection();

		// ����һ��ִ�д洢���̵��������
		CallableStatement cstms = conn.prepareCall("{call pro4(?,?)}");

		// ָ��"?"��ֵ
		cstms.setInt(1, 1);
		// ָ���ڶ����ʺ����������
		cstms.registerOutParameter(2, Types.VARCHAR);
		// ִ�д洢����
		boolean i = cstms.execute();
		// ��ȡ����ֵ
		String name = cstms.getString(2);
		System.out.println("���յ��Ĳ����ǣ�" + name);

		System.out.println(i); // true
		// �ͷ���Դ
		JdbcUtils.release(null, cstms, conn);

	}

	/**
	 * ���Դ�����ֵ�Ĵ洢����
	 * 
	 * @throws SQLException
	 */
	public static void testPro1() throws SQLException {
		// ������Ӷ���
		Connection conn = JdbcUtils.getConnection();

		// ����һ��ִ�д洢���̵��������
		CallableStatement cstms = conn.prepareCall("{call pro1(?)}");

		// ָ��"?"��ֵ
		cstms.setInt(1, 1);

		// ִ�д洢����
		boolean i = cstms.execute();
		System.out.println(i); // true
		// �ͷ���Դ
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
