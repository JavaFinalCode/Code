import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.BeforeClass;
import org.junit.AfterClass;
//通过执行语句增删改查均可：boolean execute(String sql) throws SQLException
//查询语句 ：ResultSet executeQuery(String sql) throws SQLException;
//增加、删除、修改均可：int executeUpdate(String sql) throws SQlException
//获取元素数据：ResultSetMataData getMataData() throws SQlException

//单元测试junit 依赖的jar包
//junit——4.12jar包  hamcrest-core-1.3.jar  hamcrest-library-1.3.jar 
//运行方法：java org.junit.runner.JunitCore Classname
//方法执行顺序按照方法名的字典排序

/**
*作业要求：
*使用PreparedStatement插入宠物信息
*使用PreparedStatement接口向数据表dog中插入两条狗狗的信息
*/
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class LearnJDBD
{
	public static Connection conn=null;
   
   @BeforeClass
   public static void init()
   {
   	conn = DbHelper.getInstance().getConnection();
   }	
   
   @AfterClass
   public static void destory()
   {
   	DbHelper.getInstance().closeConnection(conn);	
   }
   
   //插入数据
    @Test
    public void a_insert()
    {
    	PreparedStatement ppStat=null;
    	try
    	{
    		String sql="insert into emp(empno,ename,deptno) values(seq_empno,'陈冠希',40)";
   		stat =conn .createStatement();
   		int count=stat.executeUpdate(sql);
   		System.out.println("插入"+count+"行记录！");
    	}
    	catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//关闭资源
			DbHelper.getInstance().closeQuietly(ppStat,null);
		}    	
    }
   
   //查询数据
   @Test
   public void b_query()
   {
   	Statement Stat=null;
   	ResultSet rs = null ;
   	try
   	{
   		String sql="select * from emp";
   		Stat= conn.createStatement();
   		rs=stat.executeQuery(sql);
   		System.out.println("empno\tename\tdeptno");
   		while(rs.next())
			{
				//通过列在表中的序号获（从1开始）取资源
				System.out.print(rs.getString(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				//通过黎明方式获取数据
				System.out.println(rs.getString("deptno"));
			}  	
   	}
   	catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//关闭资源
			DbHelper.getInstance().closeQuietly(ppStat,rs);
		}
   }
   
   //修改数据
   @Test
   public void c_update()
   {
   	Statement stat=null;
   	try
   	{
   		String sql="update from set ename='阿娇' where ename='陈冠希'";
   		stat =conn .createStatement();
   		int count=stat.executeUpdate(sql);
   		System.out.println("修改"+count+"条记录！");
   	}
   	catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//关闭资源
			DbHelper.getInstance().closeQuietly(Stat,null);
		}
   }
   
   // 删除数据
   @Test
   public void e_delete()
   {
   	Statement stat=null;
   	try
   	{
   		String sql="delete from emp where ename='阿娇'";
   		stat =conn .createStatement();
   		int count=stat.executeUpdate(sql);
   		System.out.println("删除"+count+"行记录！");
   	}
   	catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//关闭资源
			DbHelper.getInstance().closeQuietly(Stat,null);
		}
   }
   
   //具有sql注入安全缺陷的写法
    public void e_executeBatch()
    {
   	
   	try
   	{
   		
   	}
   	catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//关闭资源
			DbHelper.getInstance().closeQuietly(ppStat,null);
		}
    }
    
    //预处理
    public void f_executeBatch()
    {
   	
   	try
   	{
   		
   	}
   	catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//关闭资源
			DbHelper.getInstance().closeQuietly(ppStat,null);
		}
    }
    
   //批处理   区别PreparedStatement提供的批处理和普通Statement的区别
   public void g_executeBatch()
   {  	
   	try
   	{
   		
   	}
   	catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//关闭资源
			DbHelper.getInstance().closeQuietly(ppStat,null);
		}
   }
}

//单例模式 （Singleton） 创建数据库连接
//实现方式：
//				1:创建静态常量实例
//				2:构造方法私有化
//          3:提供静态获取实例方法
//				4:添加普通方法
//优 点： 节省内存，因为它限制了实例的个数，有利于Java垃圾回收
//下面使用的饿汉模式    ———>站在用户角度：宁愿你初始化加载可以慢点，但不愿程序点了后进行慢慢等待
//重量级对象应用饿汉模式———>类加载时速度慢，但运行时速度快；
//懒汉模式与其相反      ———>类加载时速度快，但运行时第一次获得对象的速度慢。
//其他实现方式：懒汉式、考虑线程安全方式多种方式
class DbHelper
{
	private static final DbHelper INSTANCE=new DbHelper();
	
	private DbHelper(){}
	
	public static DbHelper getInstance()
	{
		return INSTANCE;	
	}	
	
	//获取数据库连接
	public Connection getConnection()
	{
		Connection conn=null;
		
		try
		{
			System.out.println("开始连接...");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");			
			System.out.println("连接成功。");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("连接失败!");
		}
		return conn;	
	}
	
	//关闭数据库连接Connection
	public void closeConnection(Connection conn)
	{
		if(null!=conn)
		{
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				System.out.println("断开连接！");
			}
		}
	}
	
	//关闭所有资源
	public void closeQuietly(Statement stat ,ResultSet rs)
	{
		closeStatement(stat);
		closeResultSet(rs);
	}
	
	//关闭Statement
	public void closeResultSet(Statement  stat)
	{
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
	}
	
	//关闭PreparedStatement
	public void closeResultSet(PreparedStatement pstmt)
	{
		if(null!=pstmt)
		{
			try
			{
				pstmt.close();
			}
				catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	//关闭结果集ResultSet
	public void closeResultSet(ResultSet rs)
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
	}
}

Connection conn = null;
PreparedStatement pstmt = null;
String sql="update dog set health=?,love=? where id=?";
pstmt = conn.prepareStatement(sql);
pstmt.setInt(1, 80);
pstmt.setInt(2, 15);
pstmt.setInt(3, 1);
pstmt.executeUpdate();


Connection conn = null;
PreparedStatement pstmt=conn.prepareStatement("insert into test values(?,?)");   
for (int i = 0; i < 1000000; i++) 
{
	pstmt.setInt(1, i);
	pstmt.setString(2, "test");
	pstmt.setInt(3, 1);	
	pstmt.addBatch();
	if(i%1000==0)
	{
		pstmt.executeBatch();
	}
}
	pstmt.executeBatch();
	conn.commit();

