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
//ͨ��ִ�������ɾ�Ĳ���ɣ�boolean execute(String sql) throws SQLException
//��ѯ��� ��ResultSet executeQuery(String sql) throws SQLException;
//���ӡ�ɾ�����޸ľ��ɣ�int executeUpdate(String sql) throws SQlException
//��ȡԪ�����ݣ�ResultSetMataData getMataData() throws SQlException

//��Ԫ����junit ������jar��
//junit����4.12jar��  hamcrest-core-1.3.jar  hamcrest-library-1.3.jar 
//���з�����java org.junit.runner.JunitCore Classname
//����ִ��˳���շ��������ֵ�����

/**
*��ҵҪ��
*ʹ��PreparedStatement���������Ϣ
*ʹ��PreparedStatement�ӿ������ݱ�dog�в���������������Ϣ
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
   
   //��������
    @Test
    public void a_insert()
    {
    	PreparedStatement ppStat=null;
    	try
    	{
    		String sql="insert into emp(empno,ename,deptno) values(seq_empno,'�¹�ϣ',40)";
   		stat =conn .createStatement();
   		int count=stat.executeUpdate(sql);
   		System.out.println("����"+count+"�м�¼��");
    	}
    	catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//�ر���Դ
			DbHelper.getInstance().closeQuietly(ppStat,null);
		}    	
    }
   
   //��ѯ����
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
				//ͨ�����ڱ��е���Ż񣨴�1��ʼ��ȡ��Դ
				System.out.print(rs.getString(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				//ͨ��������ʽ��ȡ����
				System.out.println(rs.getString("deptno"));
			}  	
   	}
   	catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//�ر���Դ
			DbHelper.getInstance().closeQuietly(ppStat,rs);
		}
   }
   
   //�޸�����
   @Test
   public void c_update()
   {
   	Statement stat=null;
   	try
   	{
   		String sql="update from set ename='����' where ename='�¹�ϣ'";
   		stat =conn .createStatement();
   		int count=stat.executeUpdate(sql);
   		System.out.println("�޸�"+count+"����¼��");
   	}
   	catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//�ر���Դ
			DbHelper.getInstance().closeQuietly(Stat,null);
		}
   }
   
   // ɾ������
   @Test
   public void e_delete()
   {
   	Statement stat=null;
   	try
   	{
   		String sql="delete from emp where ename='����'";
   		stat =conn .createStatement();
   		int count=stat.executeUpdate(sql);
   		System.out.println("ɾ��"+count+"�м�¼��");
   	}
   	catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//�ر���Դ
			DbHelper.getInstance().closeQuietly(Stat,null);
		}
   }
   
   //����sqlע�밲ȫȱ�ݵ�д��
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
			//�ر���Դ
			DbHelper.getInstance().closeQuietly(ppStat,null);
		}
    }
    
    //Ԥ����
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
			//�ر���Դ
			DbHelper.getInstance().closeQuietly(ppStat,null);
		}
    }
    
   //������   ����PreparedStatement�ṩ�����������ͨStatement������
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
			//�ر���Դ
			DbHelper.getInstance().closeQuietly(ppStat,null);
		}
   }
}

//����ģʽ ��Singleton�� �������ݿ�����
//ʵ�ַ�ʽ��
//				1:������̬����ʵ��
//				2:���췽��˽�л�
//          3:�ṩ��̬��ȡʵ������
//				4:�����ͨ����
//�� �㣺 ��ʡ�ڴ棬��Ϊ��������ʵ���ĸ�����������Java��������
//����ʹ�õĶ���ģʽ    ������>վ���û��Ƕȣ���Ը���ʼ�����ؿ������㣬����Ը������˺���������ȴ�
//����������Ӧ�ö���ģʽ������>�����ʱ�ٶ�����������ʱ�ٶȿ죻
//����ģʽ�����෴      ������>�����ʱ�ٶȿ죬������ʱ��һ�λ�ö�����ٶ�����
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
		Connection conn=null;
		
		try
		{
			System.out.println("��ʼ����...");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");			
			System.out.println("���ӳɹ���");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("����ʧ��!");
		}
		return conn;	
	}
	
	//�ر����ݿ�����Connection
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
	public void closeQuietly(Statement stat ,ResultSet rs)
	{
		closeStatement(stat);
		closeResultSet(rs);
	}
	
	//�ر�Statement
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
	
	//�ر�PreparedStatement
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
	
	
	//�رս����ResultSet
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

