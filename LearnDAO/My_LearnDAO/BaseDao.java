public class BaseDao
{	
	private static DbHelper INSTANCE =new DbHelper();
	
	private DbHelper() {}
	
	public static DbHelper getInstance()
	{
		return INSTANCE;	
	} 
	
	//��ȡ���ݿ�����
	public Connection getConnection()
	{
		//���ļ���ȡ������Ϣ
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
			LOGGER.debug("��ʼ�������ݿ����ӡ�������");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn.DriverManager.getConnection(url,userName,password);
			LOGGER.debug("���ӳɹ���");
		}
		catch(ClassNotFoundException e)
		{
			LOGGER.warn("û���ҵ������ࣺoracle.jdbc.driver.OracleDriver!");
			return null;
		}
		catch(SQLException e)
		{
			LOGGER.warn("����ʧ�ܣ�");
			return null;
		}
		return conn;
	}	
	
	//���ļ���ȡ���ݿ�������Ϣ
	public Properties getConfig()
	{
		InputStream is=null;
		Properties props=new Properties();
		try
		{
			//��ȡ��ǰCLASSPATHĿ¼�µ�conf/db��properties,����ʱ��CLASSPATHΪ
			LOGGER.debug("��ȡ�����ļ��ɹ���");
		}
		catch(FileNotFoundException e)
		{
			LOGGER.warn("�Ҳ��������ļ���");
			return null;
		}
		catch(IOException e)
		{
			LOGGER.warn("��ȡ������Ϣʧ�ܣ�");
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
	//�ر����ݿ�����Connection
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
				LOGGER.debug("�Ͽ����ݿ����ӣ�����");
			}
		}
	}
	//�ر�������Դ
	public void closeQuietly()
	{
		closeStatement(stat);
		closeResultSet(rs);
	}
	
	//�ر�Statement
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
	
	//�رս����ResultSet
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
