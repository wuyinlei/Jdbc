package com.yinlei;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.w3c.dom.css.RGBColor;

/**
 * 
 * ��ɾ�Ĳ�
 * 
 * @author wuyin
 *
 */
public class JdbcDemo4 {

	public static void main(String[] args) throws Exception {
		// 1��ע������
		Class.forName("com.mysql.jdbc.Driver");
		// 2������һ�����Ӷ���
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
		// 3������һ��sql��䷢�͵��������
		Statement stmt = conn.createStatement();

		// 4��ִ��SQL,�õ���ѯ�Ľ��������
		// String sql = "insert into stu(id,name,sex,age,address)
		// values(12,'С��','��',20,'����ʡ�һ���')"; ��
		// String sql = "UPDATE stu SET sex ='��' WHERE id = 12"; ��
		// String sql = "DELETE from stu where id = 12";
		// int i = stmt.executeUpdate(sql);
		// System.out.println("�ı����Ŀ��" + i);
		//String sql = "select id ����,name ����  from stu";
		String sql = "select * from stu";

		ResultSet rs = stmt.executeQuery(sql);
		// 5����������������

		while (rs.next()) {
			// System.out.println("id :" + rs.getInt("id") + " name :" +
			// rs.getString("name"));
			// ��һ�ָ����ֶ�������
			// int id = rs.getInt("����"); //
			// String name = rs.getString("����");
			// int age = rs.getInt("age");
			// String addr = rs.getString("address");
			// System.out.println("id :" + id + " name :" + name);
			// System.out.println("id :" + id + " name :" + name + " age :" +
			// age + " addr " + addr);

			// �ڶ����Ǹ����ֶε����� �ǽ���������(�±�� 1 ��ʼ)
			// int id = rs.getInt(1);
			// String name = rs.getString(2);
			// System.out.println("id :" + id + " name :" + name);
			
			
			/**
			 * String sql = "select * from stu";
			 * ��sql����������ʱ�򣬾Ϳ�����������ַ�ʽ������
			 */
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			String addr = rs.getString("address");
			System.out.println("id :" + id + " name :" + name + " age :" + age + " addr " + addr);
		}
		// 6���ر����ӡ�������󡢽����
		rs.close();
		stmt.close();
		conn.close();

	}

}
