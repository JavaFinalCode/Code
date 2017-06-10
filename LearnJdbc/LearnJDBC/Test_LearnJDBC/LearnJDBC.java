package com.cheer;

import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.List;
// 单元测试（方法运行顺序）
import org.junit.Test;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Ignore;
// DBUtils（返回一行、返回一个List集合）
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
// log4j(日志对象、日志管理)
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
// log4j使用：
// 日志级别：
// TRACE Level   | 级别越高
// DEBUG Level	 v
// INFO Level	
// WARN Level	
// ERROR Level	
// FATAL Level


@FixMethodOrder(MethodSorters.NAME_ASCENOING)
public class LearnJDBC
{
	//获取日志文件
	private static final Logger LOGGER = LogManager.getLogger(LearnJDBC.class);
	
	private static Connection conn= null;
	
	@beforeClass
	public static void init()
	{
		conn= DbHelper.getInstance().getConnection();
		
		if(null==conn)
		{
			LOGGER.warn("未建立数据库连接，系统退出");
			System.exit(0);	
		}	
		
	}
	
	@AfterClass
	public static void destory()
	{
		DbHelper.getInstance().getConnection(conn);	
	}
	
	//插入数据
	@Test
	@Ignore
	public void a_insert()
	{
		Statement stat= null;
		try
		{
			String sql="insert into emp(empno,ename,deptno)values (1,'陈冠希',40);";
			stat =conn.createStatement();
			int count=stat.executeUpdate(sql);
			LOGGER.debug("插入{}行数据！",count);
			Assert.assertEquals(1,count);
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//关闭资源
			DbHelper.getInstance().closeQuietly(stat,null);
		}
	}	
}
//具有sql安全缺陷的写法
	@Test
	@Ignore
	public void e_unsafelyLogin()
	{
		Statement stat =null;
		ResultSet rs= null;
		try
		{
			int empno=0;
			String ename=null;
			//正常数据 ok
			//empno = 7369;
			//ename ="SMITH";
			//String sql="select * from emp where empno=7369 and ename ='SMITH'";
			//恶意数据，构造sql注入
			empno=7369;
			ename="hacker" or "1"="1";
			String sql="select * from emp where empno= " +empno + " and ename='" + ename + " ';";
			LOGGER.debug("执行sql:{}",sql);
			stat =conn.createStatement();
			int count=stat.executeUpdate(sql);
			int counts=0;
			if(rs.next())
			{
				counts.getInt(1);
			}
			//junit断言：如果count结果为1，则测试用例通过，反之，则测试用例不通过！
			Assert.assertEquals(0,counts);			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//关闭资源
			DbHelper.getInstance().closeQuietly(stat,rs);
		}	
	}
	
	//预处理
	@Test
	@Ignore
	public void f_safetyLogin()
	{
		PreparedStatement stat = null;
		ResultSet rs = null;
		try
		{
			int empno=0;
			String ename=null;
			//正常数据 ok
			empno=7369;
			ename="SMITH";
			String sql="select count(empno) counts from emp where empno=?,ename=?;";
			LOGGER.debug("执行sql:{}",sql);
			stat =conn.PrepareStatement(sql);
			stat.setInt(1,empno);
			stat.setString(2,ename);
			int count=stat.executeUpdate(sql);
			int counts=0;
			if(rs.next())
			{
				counts.getInt(1);
			}
			//junit断言：如果count结果为1，则测试用例通过，反之，则测试用例不通过！
			Assert.assertEquals(0,counts);			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//关闭资源
			DbHelper.getInstance().closeQuietly(stat,rs);
		}		
	}
	
	//批处理
	@Test
	@Ignore
	public void h_executeBatchWithPreStat()
	{
		PreparedStatement stat = null;
		try
		{
			String sql="insert into empcopy (ename,deptno)values ('陈冠希',40);";
			stat=conn.prepareStatement(sql);
			
			for(int i=0;i<10000;i++)
			{
				stat.setString(1,"陈冠希");
				stat.setInt(2,40);
				stat.setBatch();
			}
			//批量执行
			stat.executeBatch();
			
			Assert.asserEquals(1,1);		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//关闭资源
			DbHelper.getInstance().closeQuietly(stat,null);
		}		
	}
	
	//使用DbUtils进行查询 返回一行数据、也可以返回一个List集合
	@Test
	@Ignore
	public void i_getOneRowResult()
	{
		ResultSetHandler<Emp> handler=new BeanHandler<Emp>(Emp.class)
		QueryRunner runner=new QueryRunner();
		Emp emp=null;
		try
		{
			emp=runner.query(conn,"select * from emp where emp = ?;",handler,7369);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		LOGGER.debug(emp);
	}

	@Test
	@Ignore
	public void i_getList()
	{
		ResultSetHandler<List<Emp>> handler=new BeanHandler<Emp>(Emp.class)
		QueryRunner runner=new QueryRunner();
		Emp emps=null;
		try
		{
			emps=runner.query(conn,"select * from emp;",handler);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		LOGGER.debug("查询到{}条数据",emps.size());
	}
}

//单例模式
class DbHelper
{
	//日志
	private static final Logger LOGGER=LogManager.getLogger(DbHelper.class);
	
	private static DbHelper INSTANCE =new DbHelper();
	
	private DbHelper() {}
	
	public static DbHelper getInstance()
	{
		return INSTANCE;	
	} 
	
	//获取数据库连接
	public Connection getConnection()
	{
		//从文件读取配置信息
		Properties props = this.getDbConfig();
		
		if(null==props)	
		{
			return null;	
		}	
		
		Connection conn=null;
		String url=props.getProperty("url");
		String userName=props.getProperty("username");
		String password=props.getProperty("password");
		try
		{
			LOGGER.debug("开始建立数据库连接。。。。");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn.DriverManager.getConnection(url,userName,password);
			LOGGER.debug("连接成功！");
		}
		catch(ClassNotFoundException e)
		{
			LOGGER.warn("没有找到驱动类：oracle.jdbc.driver.OracleDriver!");
			return null;
		}
		catch(SQLException e)
		{
			LOGGER.warn("连接失败！");
			return null;
		}
		return conn;
	}	
	
	//从文件读取数据库配置信息
	public Properties getConfig()
	{
		InputStream is=null;
		Properties props=new Properties();
		try
		{
			//读取当前CLASSPATH目录下的conf/db，properties,而此时的CLASSPATH为
			LOGGER.debug("读取配置文件成功！");
		}
		catch(FileNotFoundException e)
		{
			LOGGER.warn("找不到配置文件！");
			return null;
		}
		catch(IOException e)
		{
			LOGGER.warn("读取配置信息失败！");
			return null;
		}
		finally
		{
			if(null!=is)
			{
				try
				{
					is.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}			
		}
	}
	//关闭数据库连接Connection
	public void closeConnection(Connection conn)
	{
		if(null != conn)
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
				LOGGER.debug("断开数据库连接！！！");
			}
		}
	}
	//关闭所有资源
	public void closeQuietly()
	{
		closeStatement(stat);
		closeResultSet(rs);
	}
	
	//关闭Statement
	public void closeStatement(Statement stat)
	{
		if(null != stat)
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
	
	//关闭结果集ResultSet
	public void closeResultSet(ResultSet rs)
	{
		if(null != rs)
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
