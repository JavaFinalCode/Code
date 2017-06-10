import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
*jdbc:表示采用jdbc方式连接数据库   全称：(Java Data Base Connectivity,java数据库连接)
*oracle:表示连接的是oracle数据库
*thin:表示连接时采用thin模式(oracle中有两种模式)
*/
/**
*-cp 和 -classpath 一样，是指定类运行所依赖其他类的路径，通常是类库，jar包之类，需要全路径到jar包，
*window上分号“;” 分隔，linux上是分号“:”分隔。不支持通配符，需要列出所有jar包，用一点“.”代表当前路径。   
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
			System.out.println("连接成功");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("连接失败");
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
