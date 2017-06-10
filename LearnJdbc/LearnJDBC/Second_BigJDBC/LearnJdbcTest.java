//IO��
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

//�����ļ�
import java.util.Properties;

//JDBC
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;

//�׺в���
import org.junit.Test;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Ignore;



//ͨ��ִ�������ɾ�Ĳ���ɣ�boolean execute(String sql) throws SQLException
//��ѯ��䣺ResultSet executeQuery(String sql) throws SQLException
//���ӣ�ɾ�����޸ľ��ã�int executeUpdate(String sql) throws SQLException
//��ȡԪ���� ResultSetMetaData getMetaData() throws SQLException
//Ԥ���룺PreparedStatement ��ȡ����prepareStatement(String sql) throws SQLException
//�������� ��ͨ������void addBatch(String sql),addBatch(String sql)..--> int[] excuteBatch()-->throw SQLException
//  			 Ԥ����������setInt(?,?),setString(?,?)..-->addBatch()-->int[] excuteBatch()

//����  void rollback() throws SQLException
//   		void commit() throws SQLException

//��Ԫ����junit ������jar��
//junit-4.12.jar   hamcrest-core-1.3.jar  hamcrest-library-1.3.jar
//���з���  java  org.junit.runner.JUnitCore classname
//����ִ��˳���շ��������ֵ�����

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LearnJdbcTest
{
	private static Connection conn=null;
	
	@BeforeClass
	public static void init()
	{
		conn=DbHelper.getInstance().getConnection();
		
		if(null==conn)
		{
			System.out.print("δ�������ݿ����ӣ�ϵͳ�˳�");
			System.exit(0);
		}
	}
	
	@AfterClass
	public static void destory()
	{
		DbHelper.getInstance().closeConnection(conn);
	}
	
	//��ѯ
	@Test
	@Ignore//����ʱ���Ը÷���
	public void b_query()
	{
		Statement stat=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;
		try
		{	
			String sql="select * from deptcopy";
			stat=conn.createStatement();
			rs=stat.executeQuery(sql);
			rsmd=rs.getMetaData();
			int a=rsmd.getColumnCount();
			for(int i=1;i<=a;i++)
			{
				System.out.print(rsmd.getColumnName(i)+"\t");
			}
			System.out.print("\n");

     /*   
			//��ȡ����
			String str1=rsmd.getColumnName(1);
			String str2=rsmd.getColumnName(2);
			String str3=rsmd.getColumnName(3);
			System.out.println(str1+"\t"+str2+"\t"+str3);*/
			while(rs.next())
			{
				for(int j=1;j<=a;j++)
				{
					System.out.print(rs.getString(j)+"\t");
				}
				System.out.print("\n");
				
				/*
				//ͨ�����ڱ��е���ţ���1��ʼ��ȡ����
				System.out.print(rs.getString(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				//ͨ��������ʽȡ����
				System.out.print(rs.getString(3)+"\n");*/
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("*********************��ѯ");
			DbHelper.getInstance().closeAll(stat,rs);
		}
	}
	
	@Test
	@Ignore//����ʱ���Ը÷���
	//��������
	public void a_insert()
	{
		Statement stat=null;
		try
		{
			String sql="insert into deptcopy values(60,'�������ʦ','��ɽ��Ȼ')";	
			stat=conn.createStatement();
			int count=stat.executeUpdate(sql);
			System.out.println("����"+count+"�����ݣ�");
			
			//����countΪ1�������ǣ�����Բ�ͨ��
			Assert.assertEquals(1,count);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("*********************����");
			DbHelper.getInstance().closeAll(stat,null);
		}
	}
	
	@Test
	@Ignore//����ʱ���Ը÷���
	//�޸�����
	public void c_update()
	{
		Statement stat=null;
		try
		{
			String sql="update deptcopy set dname='����' where deptno=50";	
			stat=conn.createStatement();
			int count=stat.executeUpdate(sql);
			System.out.println("�޸�"+count+"�����ݣ�");
			
			//����countΪ1�������ǣ�����Բ�ͨ��
			Assert.assertEquals(1,count);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//�ر���Դ
			System.out.println("*********************�޸�");
			DbHelper.getInstance().closeAll(stat,null);
		}		
	}
	
	@Test
	@Ignore//����ʱ���Ը÷���
	//ɾ������
	public void d_delete()
	{
		Statement stat=null;
		try
		{
			String sql="delete from deptcopy where deptno=60";	
			stat=conn.createStatement();
			int count=stat.executeUpdate(sql);
			System.out.println("ɾ��"+count+"�����ݣ�");
			
			//����countΪ1�������ǣ�����Բ�ͨ��
			Assert.assertEquals(1,count);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//�ر���Դ
			System.out.println("*********************ɾ��");
			DbHelper.getInstance().closeAll(stat,null);
		}		
	}
	
	

	//Ԥ����
  @Test
  //@Ignore//����ʱ���Ը÷���
  public void f_securityLogin()
	{		
		PreparedStatement pstat=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;
		try
		{	
			int deptno=0;
		  String dname=null;
		  
		  //��������OK
		  deptno=30;
		  dname="SALES";
		  
		  //�������ݣ�����SQLע��
      //deptno=30;
		  //dname=" hacker' or '1'='1 ";
		  
		  //select * from deptcopy where deptno=30 and ename='SALES';
			String sql=" select * from deptcopy where deptno= ? and dname=? ";
			System.out.println(sql);
			pstat=conn.prepareStatement(sql);
			pstat.setInt(1,deptno);
			pstat.setString(2,dname);
			rs=pstat.executeQuery();
			rsmd=rs.getMetaData();
			int a=rsmd.getColumnCount();
			System.out.println(a);
			for(int i=1;i<=a;i++)
			{
				System.out.print(rsmd.getColumnName(i)+"\t");
			}
			System.out.print("\n");
			while(rs.next())
			{
				for(int j=1;j<=a;j++)
				{
					System.out.print(rs.getString(j)+"\t");
				}
				System.out.print("\n");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("*********************Ԥ����");
			DbHelper.getInstance().closeAll(pstat,rs);
		}
	}
	
	//����sqlע�밲ȫȱ�ݵ�д��
  @Test
  @Ignore//����ʱ���Ը÷���
  public void e_usafeLogin()
	{
		Statement stat=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;

		try
		{	
			int deptno=0;
		  String dname=null;
		  
		  //��������OK
		  //deptno=30;
		  //dname="SALES";
		  
		  //�������ݣ�����SQLע��
      deptno=30;
		  dname=" hacker' or '1'='1 ";
		  
		  //select * from deptcopy where deptno=30 and ename='SALES';
			String sql=" select * from deptcopy where deptno= " +deptno+ " and dname='"+ dname + " ' ";
			System.out.println(sql);
			stat=conn.createStatement();
			rs=stat.executeQuery(sql);
			rsmd=rs.getMetaData();
			int a=rsmd.getColumnCount();
			for(int i=1;i<=a;i++)
			{
				System.out.print(rsmd.getColumnName(i)+"\t");
			}
			System.out.print("\n");
			while(rs.next())
			{
				for(int j=1;j<=a;j++)
				{
					System.out.print(rs.getString(j)+"\t");
				}
				System.out.print("\n");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("*********************����sqlע�밲ȫȱ�ݵ�д��");
			DbHelper.getInstance().closeAll(stat,rs);
		}
	}	
	
	//������ Statement-->void addBatch(String sql),addBatch(String sql)..--> int[] excuteBatch()-->throw SQLException
  //�ŵ㣺������Ӷ�����ͬSql���
  //ȱ�㣺���ܽϲ�
	@Test
	//@Ignore//����ʱ���Ը÷���
	public void g_executeBathWithStat()
	{
		Statement stat=null;
		try
		{	
			String sql="insert into deptcopy values(70,'�������ʦ','����')";
			String sql1="insert into deptcopy values(80,'�������ʦ','007')";
			
			//���������ύ��Ĭ���Զ��ύ��
			conn.setAutoCommit(false);
			
			stat=conn.createStatement();
			stat.addBatch(sql);
			stat.addBatch(sql1);
			//����ִ��
			stat.executeBatch();
			
			stat.clearBatch();
			
			//�ֶ��ύ����
			conn.commit();
			System.out.println("������ɹ�");
			
			//���ԣ������������ͨ��
			Assert.assertEquals(1,1);
		}
		catch(SQLException e)
		{
			
			e.printStackTrace();
			
			//�����쳣������ع�����ֹ�������ƻ�
			try
			{
				conn.rollback();
				System.out.println("δ�ɹ��ع�");
			}
			catch(SQLException e1)
			{
				System.out.println("δ�ܳɹ��ع�");
			}
		}
		finally
		{
			System.out.println("*********************������");
			DbHelper.getInstance().closeAll(stat,null);
		}
	}
	
	//������ PreparedStatement-->setInt(?,?),setString(?,?)..-->addBatch()-->int[] excuteBatch()
  //�ŵ㣺���ܸߣ���ȫ
  //ȱ�㣺ֻ��ִ��һ��sql���
	@Test
	@Ignore//����ʱ���Ը÷���
	public void h_executeBathprestatment()
	{
		PreparedStatement pstat=null;
		try
		{	
			String sql="insert into deptcopy values(?,?,?)";
			pstat=conn.prepareStatement(sql);
			pstat.setInt(1,90);
			pstat.setString(2,"'ҽ��'");
			pstat.setString(3,"'�Ϻ�'");
			pstat.addBatch();
			pstat.setInt(1,92);
			pstat.setString(2,"'��ʦ'");
			pstat.setString(3,"'����'");			
			pstat.addBatch();
			pstat.executeBatch();
			System.out.println("������2�ɹ�");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("*********************������2prepare");
			DbHelper.getInstance().closeAll(pstat,null);
		}
	}
}


//����ģʽ��Singleton���������ݿ�����
//ʵ�ַ�������ʽ��
//	          1.����˽�еľ�̬����ʵ��
//						2.���췽��˽�л�
//						3.�ṩ��̬��ȡʵ������
//						4.�����ͨ����
//�ŵ�
//����ʵ�ַ�ʽ������ʽ�������̰߳�ȫ��ʽ���ַ�ʽ

class DbHelper
{
	private static final DbHelper INSTANCE=new DbHelper();
	
	private DbHelper(){}
	
	public static DbHelper getInstance()
	{
		return INSTANCE;
	}
	
	//��ȡ���ݿ�����
	public Connection getConnection()
	{
		//���ļ�conf/db.properties��ȡ���ݿ�������Ϣ
		Properties props=this.getDbconfig();
		
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
			System.out.println("��ʼ���ӡ�����");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,userName,password);
			System.out.println("���ӳɹ�������");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("û���ҵ������ࣺoracle.jdbc.driver.OracleDriver��");
			return null;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("����ʧ�ܡ�����");
			return null;
		}
		return conn;
		
	}
	
	//���ļ���ȡ���ݿ�������Ϣ
	public Properties getDbconfig()
	{
		InputStream in=null;
		Properties props=new Properties();
		
		try
		{
			//��ȡ��ǰ����classPathĿ¼�µ�conf/db.properties,���Ǻ�javawen�ļ���ͬһĿ¼����
			in=DbHelper.class.getClassLoader().getResourceAsStream("conf/db.properties");
			props.load(in);
			System.out.println("�ɹ���ȡ�����ļ�");
		}
		catch(FileNotFoundException E)
		{
			System.out.println("�Ҳ��������ļ�");
			return null;
		}
		catch(IOException e)
		{
			System.out.println("��ȡ�����ļ�ʧ��");
			return null;			
		}
		finally
		{
			if(null!=in)
			{
				try
				{
					in.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}				
			}			
		}
		return props;
	}
	
	
	
	//�ر����ݿ�����
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
				System.out.println("�Ͽ����ӣ�");
			}
		}
	}
	
//�ر�������Դ
	public void closeAll(Statement stat,ResultSet rs)
	{
		closeStatement(stat);
		closeResultSet(rs);
	}

	//�ر����ݿ����� Statement
	public static void closeStatement(Statement stat)
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
	
	
	//�ر����ݿ����� ResultSet
	public static void closeResultSet(ResultSet rs)
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