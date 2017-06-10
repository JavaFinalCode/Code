import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class PetTest
{
	public static void main(String[] args)
	{
		create();
		insert();
		executeBatch();
		query();
		update();
		delete();		
		usafeLogin();
		securityLogin();
	}
	
	private static Connection conn = null;
	
	public static void init()
	{
		conn = DbHelper.getInstance().getConnection();	
		
		if(null == conn)
		{
			System.out.println("δ�������ݿ����ӣ�ϵͳ�˳�!");
			System.exit(0);
		}
	}
	
	public static void destory()
	{
		DbHelper.getInstance().closeConnection(conn);
	}
        //������Pet
		public static void create()
		{
			Statement stat = null;
			ResultSet rs = null;
			try
			{
				String sql =" create table tbl_pet(no number(5) primary key,type varchar2(3)��name varchar2(10),sex varchar2(2),master varchar2(5))"; 
				stat = conn.createStatement(); 
				rs = stat.executeQuery(sql); 						
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				DbHelper.getInstance().closeQuiely(stat,rs);
			}
		}
		//��������
				public static void insert()
				{
					Statement stat = null;
					try
					{
						String sql ="insert into emp(no,type,name,master) values(001,'��','����','����')"; 
						stat = conn.createStatement(); 
						int count = stat.executeUpdate(sql); 
						System.out.println("����" + count +"�м�¼��");
					}catch(SQLException e)
					{
						e.printStackTrace();
					}
					finally
					{
						DbHelper.getInstance().closeQuiely(stat,null);
					}
				}
				
		//������������
		public static void executeBatch()
		{
			Statement stat = null;
			try
			{	
				String sqlInsert = "insert into emp values(202,'è','��ķ','��','����')";
				String sqlInsert1 = "insert into emp values(303,'����','���','��','����')";
				String sqlInsert2 = "insert into emp values(404,'����','�ȹ�','δ֪','����')";
				String sqlUpdate = "update student set sex = '��' where no = 001";
				stat = conn.createStatement();
				//����
				stat.addBatch(sqlInsert);
				stat.addBatch(sqlInsert1);
				stat.addBatch(sqlInsert2);
				//����
				stat.addBatch(sqlUpdate);
				//����ִ��
				stat.executeBatch(); 
				//
				stat.clearBatch();
				//�ύ����
				conn.commit();
				
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				DbHelper.getInstance().closeQuiely(stat,null);
			}		
}
		//��ѯ  	
		public static void query()
		{			
			Statement stat = null;
			ResultSet rs = null;
			try
			{
				String sql ="select * from pet"; 
				stat = conn.createStatement(); 
				rs = stat.executeQuery(sql); 
				System.out.println("no\ttype\tname\tsex\tmaster");
				while(rs.next())
				{
					//ͨ�����ڱ��е����(��1��ʼ)��ȡ����
					System.out.print(rs.getString(1)+"\t");
					System.out.print(rs.getString(2)+"\t");
					System.out.print(rs.getString(3)+"\t");
					System.out.print(rs.getString(4)+"\t");
				
				//ͨ��������ʽ��ȡ����
					System.out.println(rs.getString("master"));
				}
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				DbHelper.getInstance().closeQuiely(stat,rs);
			}
		}
		
		//�޸�����
		public static void update()
		{
			Statement stat = null;
			try
			{
				String sql ="update emp set master = '�¹�ϣ' where ename = '����'"; 
				stat = conn.createStatement(); 
				int count = stat.executeUpdate(sql); 
				System.out.println("����" + count +"�м�¼��");
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				DbHelper.getInstance().closeQuiely(stat, null);
			}
		}
		
		//ɾ������			
		public static void delete()
		{
			Statement stat = null;
			try
			{
				String sql ="delete from emp where ename = '�¹�ϣ'"; 
				stat = conn.createStatement(); 
				int count = stat.executeUpdate(sql); 
				System.out.println("ɾ��" + count +"�м�¼��");
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				DbHelper.getInstance().closeQuiely(stat,null);
			}
		}
		
	
		
		//����sqlע�밲ȫȱ�ݵ�д��
		public static void usafeLogin()
		{
			Statement stat = null;
			ResultSet rs = null;
			try
			{
				int no = 001;
				String type = "��' or '1' = '1";

				String sql ="select count(no) counts from emp where no = " + no + " and type = '" +type +"'";
				System.out.println("ִ��sql��" + sql);
				stat = conn.createStatement();
				rs = stat.executeQuery(sql);
				if(rs.next())
				{
					 rs.getInt(1);
				}				
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				DbHelper.getInstance().closeQuiely(stat, rs);
			}
		}
		
		//Ԥ����
		public static void securityLogin()
		{
			PreparedStatement stat = null;
			ResultSet rs = null;
			try
			{
				int no = 001;
				String type = "��' or '1' = '1";
				
				String sql ="select count(empno) counts from emp where empno = ? and ename = ?";
				stat = conn.prepareStatement(sql);
				stat.setInt(1,no);
				stat.setString(2,type);
				rs = stat.executeQuery();
				if(rs.next())
				{
					 rs.getInt(1);
				}
				
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				DbHelper.getInstance().closeQuiely(stat, rs);
			}
		}
		
		//������ ����PreparedStatement�ṩ�����������ͨStatement������
		


static class DbHelper
{
	private static final DbHelper INSTANCE = new DbHelper();
	
	private DbHelper(){}
	
	public static DbHelper getInstance()
	{
		return INSTANCE;
	}
	
	//��ȡ���ݿ�����
			public  Connection getConnection()
			{
				//���ļ�conf/db.properties��ȡ���ݿ�������Ϣ
				Properties props = this.getDbConfig();
				
				if(null == props)
				{
					return null;
				}
				Connection conn =null;
				String url = props.getProperty("url");
				String userName = props.getProperty("username");
				String passWord = props.getProperty("password");
				try
				{
					System.out.println("��ʼ����...."); 
					Class.forName("oracle.jdbc.driver.OracleDriver");//����JDBC����
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger"); //����oracleURL���˻�����
					System.out.println("���ӳɹ���");
				}catch(ClassNotFoundException e)
				{
					System.out.println("û���ҵ������ࣺ oracle.jdbc.driver.OracleDriver!");
					return null;
				}catch(SQLException e)
				{
					System.out.println("����ʧ�ܣ�");
				}
				return conn;
			}
			
			//���ļ���ȡ���ݿ�������Ϣ
			public Properties getDbConfig()
			{
				InputStream is = null;
				Properties props = new Properties();
				try
				{
					//��ȡ��ǰ����classpathĿ¼�µ�conf/db.properties������ʱ��classpathΪ "."
					is = DbHelper.class.getClassLoader().getResourceAsStream("conf/db.properties");
					props.load(is);
					System.out.println("�ɹ���ȡ�����ļ���");
				}catch(FileNotFoundException e)
				{
					System.out.println("�Ҳ��������ļ���");
					return null;
				}catch(IOException e)
				{
					System.out.println("��ȡ�����ļ�ʧ�ܣ�");
					return null;
				}
				finally
				{
					if(null != is)
					{
						try
						{
							is.close();
						}catch(IOException e)
						{
							e.printStackTrace();
						}
					}
				}
				return props;
			}
			
			//�ر����ݿ�����
			public static void closeConnection(Connection conn)
			{
				if(null != conn)
				{
					try
					{
						conn.close();
					}catch(SQLException e)
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
			public void closeQuiely(Statement stat, ResultSet rs)
			{
				closeStatement(stat);
				closeResultSet(rs);
			}
			
			//�ر�Statement
			public static void closeStatement(Statement stat)
			{
				if(null != stat)
				{
					try
					{
						stat.close();
					}catch(SQLException e)
					{
						e.printStackTrace();
					}			
				}
			}
					
			//�رս����ResultSet
			public static void closeResultSet(ResultSet rs)
			{
				if(null != rs)
				{
					try
					{
						rs.close();
					}catch(SQLException e)
					{
						e.printStackTrace();
					}			
				}
			}
}
}
