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
import java.util.List;

//DBUtils
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.Handlers.BeanHandler;
import org.apache.commons.dbutils.Handlers.BeanListHandler;

//�׺в���
import org.junit.Test;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Ignore;

public class LearnJdbcTestDog
{
	//�׺в����µ���ʼ�����
	//��ʼ-->�õ�����connection
	//�м�������Է���;
	//����connection�ر���Դ;�����м䷽����ͣ������Դ
	
	private static Connection conn=null;

//����ǰ׼���������������ݿ�����	
	@BeforeClass
	public static void init()
	{
		//�õ�ʵ����ͨ��ʵ����ȡ���Ӷ��󣬺ô������²��Թ���ͬһ���󣬽�ʡ��Դ
		conn=DbHelper.getInstance().getConnection();
		
		if(null==conn)
		{
			System.out.print("δ�������ݿ����ӣ�ϵͳ�˳�");
			System.exit(0);
		}
	}	
	
	//�ر�����
	@AfterClass
	public static void destory()
	{
		DbHelper.getInstance().closeConnection(conn);
	}
	
	//����
	@Test
	@Ignore
	public void a_insert()
	{
		Statement stat=null;
		
		try
		{
			String sql="insert into dog values(3,'�ϴ�'��5,'ĸ'��'��ʿ��',2)";
			stat=conn.createStatement();
			int count=stat.executeUpdate(sql);
			
			//Ԥ��
			Assert.assertEquals(1,count);
			
			System.out.println("�ɹ�����"+count+"�����ݣ�");
		}
		
		catch(SQLException e)
		{
			System.out.println("����ʧ��");
			e.printStackTrace();
		}
		
		finally
		{
			DbHelper.getInstance().closeAll(stat,null);
		}
	}
	
	//��ѯ
	@Test
	@Ignore
	public void b_select()
	{
		Statement stat=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;
		
		try
		{
			String sql="select * from dog d,master m where d.mno=m.mno";
			stat=conn.createStatement();
			rs=stat.executeQuery(sql);
			rsmd=rs.getMetaData();
			
			//����Ŀ
			int a=rsmd.getColumnCount();
			//��ȡ����
			for(int i=1;i<=a;i++)
			{
				System.out.print(rsmd.getColumnName(i)+"\t");
			}
			System.out.print("\n");
			
			//��ȡ��ֵ
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
			DbHelper.getInstance().closeAll(stat,rs);
		}
	}
	
	//����
	@Test
	@Ignore
	public void c_update()
	{
		Statement stat=null;
		
		try
		{
			String sql="update master set mname='��˼��' where mno=2";
			stat=conn.createStatement();
			int count = stat.executeUpdate(sql);
			System.out.println("�ɹ��޸�"+count+"�����ݣ�");
			Assert.assertEquals(1,count);	
		}
		catch(SQLException e)
		{
			System.out.println("�޸�ʧ�ܣ�");
			e.printStackTrace();
		}
		finally
		{
			DbHelper.getInstance().closeAll(stat,null);
		}		
	}
	
	//ɾ��
	@Test
	@Ignore
	public void d_delete()
	{
		Statement stat=null;
		
		try
		{
			String sql="delete from master where mno=4";
			stat=conn.createStatement();
			int count = stat.executeUpdate(sql);
			System.out.println("�ɹ�ɾ��"+count+"�����ݣ�");
			Assert.assertEquals(1,count);	
		}
		catch(SQLException e)
		{
			System.out.println("ɾ��ʧ�ܣ�");
			e.printStackTrace();
		}
		finally
		{
			DbHelper.getInstance().closeAll(stat,null);
		}		
	}
	
	//����sqlע�밲ȫȱ�ݵ�д��,���ڵ���
	@Test
	@Ignore
	public void e_usafeLogin()
	{
		Statement stat=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;

		try
		{	
			int mno=0;
		  String mname=null;
		  
		  //��������OK
		  //mno=2;
		  //mname="��˼��";
		  
		  //�������ݣ�����SQLע��
      mno=2;
		  mname=" hacker' or '1'='1 ";
		  
		  //select * from master where mno=2 and ename='��˼��';
			String sql=" select * from master where mno= " +mno+ " and mname='"+ mname + " ' ";
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
			System.out.print("��ѯʧ��");
			e.printStackTrace();
		}
		finally
		{
			DbHelper.getInstance().closeAll(stat,rs);
		}		
	}
	
	//Ԥ����
	@Test
	@Ignore
	public void f_safeLogin()
	{
		PreparedStatement pstat=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;

		try
		{	
			int mno=0;
		  String mname=null;
		  
		  //��������OK
		  //mno=2;
		  //mname="��˼��";
		  
		  //�������ݣ�����SQLע��
      mno=2;
		  mname=" hacker' or '1'='1 ";
		  
		  //select * from master where mno=2 and ename='��˼��';
			String sql=" select * from master where mno=? and mname=?";
			System.out.println(sql);
			pstat=conn.prepareStatement(sql);
			
			pstat.setInt(1,mno);
			pstat.setString(2,mname);
			
			rs=pstat.executeQuery();
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
			System.out.print("��ѯʧ��");
			e.printStackTrace();
		}
		finally
		{
			DbHelper.getInstance().closeAll(pstat,rs);
		}		
	}
	
	//������ Statement-->void addBatch(String sql),addBatch(String sql)..--> int[] excuteBatch()-->throw SQLException
  //�ŵ㣺������Ӷ�����ͬSql���
  //ȱ�㣺���ܽϲ�
	@Test
	public void g_executeBathWithStat()
	{
		Statement stat=null;
		try
		{	
			String sql="insert into dog values(4,'�ϴ�'��2,'ĸ'��'��ĦҮ',3)";
			String sql1="update master set mname='����' where mno=3";
			
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
				System.out.println("�ɹ��ع�");
			}
			catch(SQLException e1)
			{
				System.out.println("δ�ܳɹ��ع�");
			}
		}
		finally
		{
			DbHelper.getInstance().closeAll(stat,null);
		}
	}

//ʹ�õ�������api apache��DBUtils
//https://commons.apache.org/proper/commons-dbutils/examples.html
//QueryRunner
//ResultSetHandler
//public <T> T query(Connection conn, String sql, Object[] params, ResultSetHandler<T> rsh) throws SQLException	
@Test
@Ignore
public void a_getOneRowsResult()
{
	ResultSetHandler<EMP> handler = new BeanListHandler<EMP>(EMP.class);
	QueryRunner runner = new QUeryRunner();
		
	EMP emp = null;
	try
	{
		emp = runner.query(conn,"select * from emp where empno = ?",handler,7369);
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	System.out.println(emp);
}
		
@Test
//@Ignore
public void b_getOneRowsList()
{
	ResultSetHandler<List<EMP>> handler = new BeanListHandler<EMP>(EMP.class);
	QueryRunner runner = new QUeryRunner();
	
	EMP emp = null;
	try
	{
		emps = runner.query(conn,"select * from emp",handler);
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	System.out.println(emps);
}

	
	
	//������ PreparedStatement-->setInt(?,?),setString(?,?)..-->addBatch()-->int[] excuteBatch()
  //�ŵ㣺���ܸߣ���ȫ
  //ȱ�㣺ֻ��ִ��һ��sql���
	@Test
	public void h_executeBathprestatment()
	{
		PreparedStatement pstat=null;
		try
		{	
			String sql="insert into master values(?,?)";
			pstat=conn.prepareStatement(sql);
			pstat.setInt(1,4);
			pstat.setString(2,"����");
			pstat.addBatch();
			pstat.setInt(1,5);
			pstat.setString(2,"Ǯ��");			
			pstat.addBatch();
			pstat.executeBatch();
			
			conn.commit();
			System.out.println("������2�ɹ�");
		}
		catch(SQLException e)
		{
			
			e.printStackTrace();
			
			//�����쳣������ع�����ֹ�������ƻ�
			try
			{
				conn.rollback();
				System.out.println("�ɹ��ع�");
			}
			catch(SQLException e1)
			{
				System.out.println("δ�ܳɹ��ع�");
			}
		}
		finally
		{
			System.out.println("*********************������2prepare");
			DbHelper.getInstance().closeAll(pstat,null);
		}
	}			
}


//����������
//����connection���ӣ��ر���Դ
//	          1.����˽�еľ�̬����ʵ��
//						2.���췽��˽�л�
//						3.�ṩ��̬��ȡʵ������
//						4.�����ͨ����
class DbHelper
{
	//1.����˽�еľ�̬����ʵ��
	private static final DbHelper INSTANCE=new DbHelper();
	
	//2.���췽��˽�л�
	private DbHelper(){}
	
	//3.�ṩ��̬��ȡʵ������
	public static DbHelper getInstance()
	{
		return INSTANCE;
	}
	
	//4.�����ͨ����
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
			//��������
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//�������Ӷ���
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

class getEmp
{
	private String empno;
	private String ename;
	private String job;
	private String mgr;
	private String hiredate;
	private String sal;
	private String comm;
	private String deptno;
   
   public string getEmpNo()
   {
   	return empno;
   }   
   public void setEmpNo(String empno)
   {
   	this.empno=empno;
   }
   
   public string getEname()
   {
   	return ename;
   }   
   public void setEname(String ename)
   {
   	this.ename=ename;
   }
   
   public string getJob()
   {
   	return job;
   }   
   public void setJob(String job)
   {
   	this.job=job;
   }
   
   public string getMgr()
   {
   	return mgr;
   }   
   public void setMgr(String mgr)
   {
   	this.mgr=mgr;
   }
   
   public string getHireDate()
   {
   	return ename;
   }  
   public void setHireDate(String hiredate)
   {
   	this.hiredate=hiredate;
   }
   
   public string getSal()
   {
   	return sal;
   }   
   public void setSal(String sal)
   {
   	this.sal=sal;
   }
   
   public string getComm()
   {
   	return comm;
   }  
   public void setComm(String comm)
   {
   	this.comm=comm;
   }
   
   public string getDeptNo()
   {
   	return deptno;
   }   
   public void setDeptNo(String deptno)
   {
   	this.deptno=deptno;
   }
      
   public String toString()
   {
   	return "empno: "+empno+"eName:"+ename  +"job:"+job  +"mgr:"+mgr+"hiredate:"+hiredate+"sal:"+sal+"comm:"+comm+"deptno:"+deptno;
   }
}


				