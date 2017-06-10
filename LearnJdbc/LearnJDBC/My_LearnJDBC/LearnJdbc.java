package com.cheer;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class LearnJdbc
{
	public static void main(String[] args)
	{
		String sqlquery = "Select * from student";
		query(sqlquery);
		System.out.println("***************************************");
		String sqlinsert = "insert into student values('2008','haha',28,'Ů','ҽ��')";
		insert(sqlinsert);
		query(sqlquery);
		System.out.println("***************************************");
		String sqldelect = "delete from student where sno='2008'";
		delect(sqldelect);
		query(sqlquery);
		System.out.println("***************************************");
		String sqlupdate = "update student set age=23,sex='��',dept='.NET' where sno='2001'";
		update(sqlupdate);
		query(sqlquery);
	}

	// ��ȡ���ݿ�����
	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			System.out.println("��ʼ����.....");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			System.out.println("���ӳɹ�");
		} 
		
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} 
		
		catch (SQLException e)
		{
			e.printStackTrace();
			System.out.print("����ʧ��");
		}
		
		return conn;
	}

	// ��ѯ
	public static void query(String str)
	{
		Connection conn = getConnection();
		Statement stat = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		
		try
		{
			stat = conn.createStatement();
			rs = stat.executeQuery(str);
			rsmd = rs.getMetaData();
			int a = rsmd.getColumnCount();
			
			for (int i = 1; i <= a; i++)
			{
				System.out.print(rsmd.getColumnName(i) + "\t");
			}
			System.out.print("\n");

			/*
			 * //��ȡ���� String str1=rsmd.getColumnName(1); String
			 * str2=rsmd.getColumnName(2); String str3=rsmd.getColumnName(3);
			 * System.out.println(str1+"\t"+str2+"\t"+str3);
			 */
			while (rs.next())
			{
				
				for (int j = 1; j <= a; j++)
				{
					System.out.print(rs.getString(j) + "\t");
				}
				System.out.print("\n");
				

				/*
				 * //ͨ�����ڱ��е���ţ���1��ʼ��ȡ���� System.out.print(rs.getString(1)+"\t");
				 * System.out.print(rs.getString(2)+"\t"); //ͨ��������ʽȡ����
				 * System.out.print(rs.getString(3)+"\n");
				 */
			}
		} 
		
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
		
		finally
		{
			// �ر���Դ,ע��˳��
			closeResultSet(rs);
			closeStatement(stat);
			closeConnection(conn);
		}
	}

	public static void insert(String str)
	{
		Connection conn = getConnection();
		Statement stat = null;
		
		try
		{
			stat = conn.createStatement();
			System.out.println(stat.executeUpdate(str));
			System.out.println("����ɹ���");

		} 
		
		catch (SQLException e)
		{
			System.out.println("����ʧ�ܣ�");
			e.printStackTrace();
		} 
		
		finally
		{
			// �ر���Դ,ע��˳��
			closeStatement(stat);
			closeConnection(conn);
		}
	}

	public static void delect(String str)
	{
		Connection conn = getConnection();
		Statement stat = null;
		try
		{
			stat = conn.createStatement();
			System.out.println(stat.executeUpdate(str));
			System.out.println("ɾ���ɹ���");

		} 
		
		catch (SQLException e)
		{
			System.out.println("ɾ��ʧ�ܣ�");
			e.printStackTrace();
		} 
		
		finally
		{
			// �ر���Դ,ע��˳��
			closeStatement(stat);
			closeConnection(conn);
		}
	}

	public static void update(String str)
	{
		Connection conn = getConnection();
		Statement stat = null;
		try
		{
			stat = conn.createStatement();
			System.out.println(stat.executeUpdate(str));
			System.out.println("���³ɹ���");
		} 
		
		catch (SQLException e)
		{
			System.out.println("����ʧ�ܣ�");
			e.printStackTrace();
		} 
		
		finally
		{
			// �ر���Դ,ע��˳��
			closeStatement(stat);
			closeConnection(conn);
		}
	}

	// �ر����ݿ����� Connection
	public static void closeConnection(Connection conn)
	{
		if (null != conn)
		{
			try
			{
				conn.close();
			} 
			
			catch (SQLException e)
			{
				e.printStackTrace();
			} 
			
			finally
			{
				System.out.println("�Ͽ����ӣ�");
			}
		}
	}

	// �ر����ݿ����� Statement
	public static void closeStatement(Statement stat)
	{
		if (null != stat)
		{
			try
			{
				stat.close();
			} 
			
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	// �ر����ݿ����� ResultSet
	public static void closeResultSet(ResultSet rs)
	{
		if (null != rs)
		{
			try
			{
				rs.close();
			} 
			
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}