package com.cheer.util;

import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

// log4j
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

// 单例模式（Singleton）创建数据库连接
// 实现方式（饿汉式）
//			 1、创建静态常量实例；
//			 2、构造方法私有化；
//			 3、提供静态获取实例方法；
// 			 4、添加普通方法。
// 优点：
// 其他实现方式：懒汉式、考虑线程安全方式多种方式。
public class DbHelper
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