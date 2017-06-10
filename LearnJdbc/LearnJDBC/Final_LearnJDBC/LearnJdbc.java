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

// 单元测试
import org.junit.Test;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Ignore;

// DBUtils
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

// log4j
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

// 通用执行语句增删改查均可：boolean execute(String sql) throws SQLException;
// 查询语句：ResultSet executeQuery(String sql) throws SQLException;
// 增加、删除、修改均用：int executeUpdate(String sql) throws SQLException;
// 获取元数据：ResultSetMetaData getMetaData() throws SQLException;
// 预编译：PreparedStatement prepareStatement(String sql) throws SQLException
// 批处理：void addBatch(String sql) throws SQLException
//		  int[] executeBatch() throws SQLException
// 事务：void rollback() throws SQLException
//		void commit() throws SQLException

// 单元测试junit 依赖的jar包：
// junit-4.12.jar hamcrest-core-1.3.jar hamcrest-library-1.3.jar
// 编译：javac -cp .;xxx.jar;yyy.jar java_file_name
// 运行方法：java org.junit.runner.JUnitCore class_name
// 方法执行顺序按照方法名的字典排序

// log4j使用：
// 日志级别：
// TRACE Level   | 级别越高
// DEBUG Level	 v
// INFO Level	
// WARN Level	
// ERROR Level	
// FATAL Level
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LearnJdbc
{	

	// 日志文件
	private static final Logger LOGGER = LogManager.getLogger(LearnJdbc.class);

	private static Connection conn = null;

	@BeforeClass
	public static void init()
	{
		conn = DbHelper.getInstance().getConnection();

		if (null == conn)
		{
			LOGGER.warn("未建立数据库连接，系统退出！");
			System.exit(0);
		}
	}

	@AfterClass
	public static void destory()
	{
		DbHelper.getInstance().closeConnection(conn);
	}

	// 插入数据
	@Test
	@Ignore // 忽略该方法
	public void a_insert()
	{
		Statement stat = null;
		try
		{
			String sql = "insert into emp(empno, ename, deptno) values(seq_empno.nextval, '陈冠希', 40)";
			stat = conn.createStatement();
			int count = stat.executeUpdate(sql);
			LOGGER.debug("插入{}行记录！", count);
			Assert.assertEquals(1, count);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// 关闭资源
			DbHelper.getInstance().closeQuietly(stat, null);
		}
	}

	// 查询
	@Test
	@Ignore
	public void b_query()
	{
		Statement stat = null;
		ResultSet rs = null;
		try
		{
			String sql = "select * from emp";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			LOGGER.debug("empno\tename\tdeptno");
			while (rs.next())
			{
				// 通过列在表中的序号获（从1开始）取数据
				LOGGER.debug(rs.getString(1) + "\t");
				LOGGER.debug(rs.getString(1) + "\t");

				// 通过列名方式获取数据
				LOGGER.debug(rs.getString("deptno"));
			}
			Assert.assertTrue(true);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// 关闭资源
			DbHelper.getInstance().closeQuietly(stat, rs);
		}				
	}

	// 修改数据
	@Test
	@Ignore // 忽略该方法
	public void c_update()
	{
		Statement stat = null;
		try
		{
			String sql = "update emp set ename = '阿娇' where ename = '陈冠希'";
			stat = conn.createStatement();
			int count = stat.executeUpdate(sql);
			LOGGER.debug("更新{}行记录！", count);
			Assert.assertEquals(1, count);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// 关闭资源
			DbHelper.getInstance().closeQuietly(stat, null);
		}
	}

	// 删除数据
	@Test
	@Ignore // 忽略该方法
	public void d_delete()
	{
		Statement stat = null;
		try
		{
			String sql = "delete from emp where ename = '阿娇'";
			stat = conn.createStatement();
			int count = stat.executeUpdate(sql);
			LOGGER.debug("删除{}行记录！", count);
			Assert.assertEquals(1, count);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// 关闭资源
			DbHelper.getInstance().closeQuietly(stat, null);
		}
	}

	// 具有sql注入安全缺陷的写法
	@Test
	@Ignore // 忽略该方法
	public void e_unsafelyLogin()
	{
		Statement stat = null;
		ResultSet rs = null;
		try
		{
			int empno = 0;
			String ename = null;

			// 正常数据 ok
			// empno = 7369;
			// ename = "SMITH";

			// 恶意数据，构造sql注入
			empno = 7369;
			ename = "hacker' or '1' = '1";

			// select * from emp where empno = 7369 and ename = 'SMITH'
			String sql = "select count(empno) counts from emp where empno = " + empno + " and ename = '" + ename + "'";
			LOGGER.debug("执行sql：{}", sql);
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			int counts = 0;
			if (rs.next())
			{
				counts = rs.getInt(1);
			}

			// jUnit断言：如果预期结果值counts为1，则测试用例通过，否则，反之。
			Assert.assertNotEquals(0, counts);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// 关闭资源
			DbHelper.getInstance().closeQuietly(stat, rs);
		}		
	}

	// 预处理
	@Test
	@Ignore
	public void f_safelyLogin()
	{
		PreparedStatement stat = null;
		ResultSet rs = null;
		try
		{
			int empno = 0;
			String ename = null;

			// 正常数据 ok
			empno = 7369;
			ename = "SMITH";

			// 恶意数据，构造sql注入
			// empno = 7369;
			// ename = "hacker' or '1' = '1";

			// select * from emp where empno = 7369 and ename = 'SMITH'
			String sql = "select count(empno) counts from emp where empno = ? and ename = ?";
			stat = conn.prepareStatement(sql);
			stat.setInt(1, empno);
			stat.setString(2, ename);
			rs = stat.executeQuery();
			int counts = 0;
			if (rs.next())
			{
				counts = rs.getInt(1);
			}

			// jUnit断言：如果预期结果值counts为1，则测试用例通过，否则，反之。
			Assert.assertNotEquals(0, counts);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// 关闭资源
			DbHelper.getInstance().closeQuietly(stat, rs);
		}				
	}

	// 批处理 区别PreparedStatement提供的批处理和普通Statement的区别
	// Statement
	// 优点：可以执行多条不同sql语句
	// 缺点：性能较差。
	@Test
	@Ignore
	public void g_executeBatchWithStat()
	{
		Statement stat = null;
		try
		{
			String sqlInsert = "insert into empcopy(empno, ename, deptno) values(seq_empno.nextval, '陈冠希', 40)";
			String sqlUpdate = "update empcopy set ename = '阿娇' where deptno = 40";
			String sqlDelete = "delete from empcopy";

			// 设置事务，手动提交
			conn.setAutoCommit(false);
			stat = conn.createStatement();

			for (int i = 0; i < 100; i++)
			{
				// 插入语句
				stat.addBatch(sqlInsert);
			}

			// 更新数据
			stat.addBatch(sqlUpdate);
			
			// 删除数据
			stat.addBatch(sqlDelete);

			// 批量执行			
			stat.executeBatch();

			stat.clearBatch();

			// 手动事务提交
			conn.commit();

			Assert.assertEquals(1, 1);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			try
			{
				conn.rollback();
			}
			catch(SQLException e1)
			{
				e1.printStackTrace();
			}
		}
		finally
		{
			// 关闭资源
			DbHelper.getInstance().closeQuietly(stat, null);
		}
	}


	// 批处理 区别PreparedStatement提供的批处理和普通Statement的区别
	// PreparedState
	// 优点：性能高，安全。
	// 缺点：只能执行单一条sql语句
	@Test
	@Ignore
	public void h_executeBatchWithPreStat()
	{
		PreparedStatement stat = null;
		try
		{
			String sql = "insert into empcopy(empno, ename, deptno) values(seq_empno.nextval, ?, ?)";
			stat = conn.prepareStatement(sql);

			for (int i = 0; i < 1000000; i++)
			{
				stat.setString(1, "陈冠希");
				stat.setInt(2, 40);
				stat.addBatch();
			}
			
			// 批量执行
			stat.executeBatch();

			Assert.assertEquals(1, 1);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// 关闭资源
			DbHelper.getInstance().closeQuietly(stat, null);
		}
	}


	// 使用第三方api apache的DBUtils
	// http://commons.apache.org/proper/commons-dbutils/examples.html
	// QueryRunner
	// ResultSetHandler
	// query(Connection conn, String sql, ResultSetHandler<T> rsh, Object... params)
	// throws SQLException
	@Test
	@Ignore
	public void i_getOneRowResult()
	{
		ResultSetHandler<Emp> handler = new BeanHandler<Emp>(Emp.class);
		QueryRunner runner = new QueryRunner();

		Emp emp = null;
		try
		{
			emp = runner.query(conn, "select * from emp where empno = ?", handler, 7369);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		LOGGER.debug(emp);
	}

	@Test
	//@Ignore
	public void j_getOneRowsList()
	{
		ResultSetHandler<List<Emp>> handler = new BeanListHandler<Emp>(Emp.class);
		QueryRunner runner = new QueryRunner();

		List<Emp> emps = null;
		try
		{
			emps = runner.query(conn, "select * from emp", handler);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		LOGGER.debug("查询到{}记录", emps.size());
	}
}


// 单例模式（Singleton）创建数据库连接
// 实现方式（饿汉式）
//			 1、创建静态常量实例；
//			 2、构造方法私有化；
//			 3、提供静态获取实例方法；
// 			 4、添加普通方法。
// 优点：
// 其他实现方式：懒汉式、考虑线程安全方式多种方式。
class DbHelper
{
	// 日志
	private static final Logger LOGGER = LogManager.getLogger(DbHelper.class);

	private static final DbHelper INSTANCE = new DbHelper();

	private DbHelper(){}

	public static DbHelper getInstance()
	{
		return INSTANCE;
	}

	// 获取数据库连接
	public Connection getConnection()
	{
		// 从文件conf/db.properties读取数据库配置信息
		Properties props = this.getDbConfig();

		if (null == props)
		{
			return null;
		}

		Connection conn = null;
		String url = props.getProperty("url");
		String userName = props.getProperty("username");
		String password = props.getProperty("password");
		try
		{
			LOGGER.debug("开始建立数据库连接...");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, userName, password);
			LOGGER.debug("连接成功！");
		}
		catch(ClassNotFoundException e)
		{
			LOGGER.warn("没有找到驱动类：oracle.jdbc.driver.OracleDriver！");
			return null;
		}
		catch(SQLException e)
		{
			LOGGER.warn("连接失败！");
			return null;
		}
		return conn;			
	}

	// 从文件读取数据库配置信息
	public Properties getDbConfig()
	{
		InputStream is = null;
		Properties props = new Properties();
		try
		{
			// 读取当前程序classpath目录下的conf/db.properties，而此时的classpath为.
			is = DbHelper.class.getClassLoader().getResourceAsStream("conf/db.properties");
			props.load(is);
			LOGGER.debug("成功读取配置文件！");
		}
		catch(FileNotFoundException e)
		{
			LOGGER.warn("找不到配置文件！");
			return null;
		}
		catch(IOException e)
		{
			LOGGER.warn("读取配置文件失败");
			return null;
		}
		finally
		{
			if (null != is)
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
		return props;		
	}

	// 关闭数据库连接Connection
	public void closeConnection(Connection conn)
	{
		if (null != conn)
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
				LOGGER.debug("断开数据库连接！！");
			}			
		}	
	}

	// 关闭所有资源
	public void closeQuietly(Statement stat, ResultSet rs)
	{
		closeStatement(stat);
		closeResultSet(rs);
	}

	// 关闭Statement
	public void closeStatement(Statement stat)
	{
		if (null != stat)
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

	// 关闭结果集ResultSet
	public void closeResultSet(ResultSet rs)
	{
		if (null != rs)
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