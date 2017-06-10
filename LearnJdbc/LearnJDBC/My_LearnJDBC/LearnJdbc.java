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
		String sqlinsert = "insert into student values('2008','haha',28,'女','医生')";
		insert(sqlinsert);
		query(sqlquery);
		System.out.println("***************************************");
		String sqldelect = "delete from student where sno='2008'";
		delect(sqldelect);
		query(sqlquery);
		System.out.println("***************************************");
		String sqlupdate = "update student set age=23,sex='男',dept='.NET' where sno='2001'";
		update(sqlupdate);
		query(sqlquery);
	}

	// 获取数据库连接
	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			System.out.println("开始连接.....");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			System.out.println("连接成功");
		} 
		
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} 
		
		catch (SQLException e)
		{
			e.printStackTrace();
			System.out.print("连接失败");
		}
		
		return conn;
	}

	// 查询
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
			 * //获取列名 String str1=rsmd.getColumnName(1); String
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
				 * //通过列在表中的序号（从1开始）取数据 System.out.print(rs.getString(1)+"\t");
				 * System.out.print(rs.getString(2)+"\t"); //通过列名方式取数据
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
			// 关闭资源,注意顺序
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
			System.out.println("插入成功！");

		} 
		
		catch (SQLException e)
		{
			System.out.println("插入失败！");
			e.printStackTrace();
		} 
		
		finally
		{
			// 关闭资源,注意顺序
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
			System.out.println("删除成功！");

		} 
		
		catch (SQLException e)
		{
			System.out.println("删除失败！");
			e.printStackTrace();
		} 
		
		finally
		{
			// 关闭资源,注意顺序
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
			System.out.println("更新成功！");
		} 
		
		catch (SQLException e)
		{
			System.out.println("更新失败！");
			e.printStackTrace();
		} 
		
		finally
		{
			// 关闭资源,注意顺序
			closeStatement(stat);
			closeConnection(conn);
		}
	}

	// 关闭数据库连接 Connection
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
				System.out.println("断开连接！");
			}
		}
	}

	// 关闭数据库连接 Statement
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

	// 关闭数据库连接 ResultSet
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