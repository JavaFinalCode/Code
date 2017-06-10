public class BaseDao
{	
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
	public void closeStatement(PreparedStatement stat)
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

}
