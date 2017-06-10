import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
*jdbc:��ʾ����jdbc��ʽ�������ݿ�   ȫ�ƣ�(Java Data Base Connectivity,java���ݿ�����)
*oracle:��ʾ���ӵ���oracle���ݿ�
*thin:��ʾ����ʱ����thinģʽ(oracle��������ģʽ)
*/
/**
*-cp �� -classpath һ������ָ���������������������·����ͨ������⣬jar��֮�࣬��Ҫȫ·����jar����
*window�Ϸֺš�;�� �ָ���linux���Ƿֺš�:���ָ�����֧��ͨ�������Ҫ�г�����jar������һ�㡰.������ǰ·����   
*
*/  
public class SQLjdbc
{
	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");			
			System.out.println("���ӳɹ�");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("����ʧ��");
		}
		return conn;		
	}
	
	public static void main(String[] args)
	{
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		String sql = "select * from emp";
		try
		{
			con = getConnection();
			stat = con.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next())
			{
				System.out.print(rs.getString(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.print(rs.getString("deptno")+"\r\n");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(null!=rs)
			{
				try
				{
					rs.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(null!=stat)
			{
				try
				{
					stat.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(null!=con)
			{
				try
				{					
					con.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
