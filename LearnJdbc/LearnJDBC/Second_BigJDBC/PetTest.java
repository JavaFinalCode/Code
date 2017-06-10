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
			System.out.println("未建立数据库连接，系统退出!");
			System.exit(0);
		}
	}
	
	public static void destory()
	{
		DbHelper.getInstance().closeConnection(conn);
	}
        //创建表Pet
		public static void create()
		{
			Statement stat = null;
			ResultSet rs = null;
			try
			{
				String sql =" create table tbl_pet(no number(5) primary key,type varchar2(3)，name varchar2(10),sex varchar2(2),master varchar2(5))"; 
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
		//插入数据
				public static void insert()
				{
					Statement stat = null;
					try
					{
						String sql ="insert into emp(no,type,name,master) values(001,'狗','旺财','李三')"; 
						stat = conn.createStatement(); 
						int count = stat.executeUpdate(sql); 
						System.out.println("插入" + count +"行记录！");
					}catch(SQLException e)
					{
						e.printStackTrace();
					}
					finally
					{
						DbHelper.getInstance().closeQuiely(stat,null);
					}
				}
				
		//批量插入数据
		public static void executeBatch()
		{
			Statement stat = null;
			try
			{	
				String sqlInsert = "insert into emp values(202,'猫','汤姆','雌','张四')";
				String sqlInsert1 = "insert into emp values(303,'鹦鹉','翡翠','雄','王五')";
				String sqlInsert2 = "insert into emp values(404,'金鱼','橙光','未知','赵六')";
				String sqlUpdate = "update student set sex = '雌' where no = 001";
				stat = conn.createStatement();
				//插入
				stat.addBatch(sqlInsert);
				stat.addBatch(sqlInsert1);
				stat.addBatch(sqlInsert2);
				//更新
				stat.addBatch(sqlUpdate);
				//批量执行
				stat.executeBatch(); 
				//
				stat.clearBatch();
				//提交事物
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
		//查询  	
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
					//通过列在表中的序号(从1开始)获取数据
					System.out.print(rs.getString(1)+"\t");
					System.out.print(rs.getString(2)+"\t");
					System.out.print(rs.getString(3)+"\t");
					System.out.print(rs.getString(4)+"\t");
				
				//通过列名方式获取数据
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
		
		//修改数据
		public static void update()
		{
			Statement stat = null;
			try
			{
				String sql ="update emp set master = '陈冠希' where ename = '张四'"; 
				stat = conn.createStatement(); 
				int count = stat.executeUpdate(sql); 
				System.out.println("更新" + count +"行记录！");
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				DbHelper.getInstance().closeQuiely(stat, null);
			}
		}
		
		//删除数据			
		public static void delete()
		{
			Statement stat = null;
			try
			{
				String sql ="delete from emp where ename = '陈冠希'"; 
				stat = conn.createStatement(); 
				int count = stat.executeUpdate(sql); 
				System.out.println("删除" + count +"行记录！");
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				DbHelper.getInstance().closeQuiely(stat,null);
			}
		}
		
	
		
		//具有sql注入安全缺陷的写法
		public static void usafeLogin()
		{
			Statement stat = null;
			ResultSet rs = null;
			try
			{
				int no = 001;
				String type = "狗' or '1' = '1";

				String sql ="select count(no) counts from emp where no = " + no + " and type = '" +type +"'";
				System.out.println("执行sql：" + sql);
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
		
		//预处理
		public static void securityLogin()
		{
			PreparedStatement stat = null;
			ResultSet rs = null;
			try
			{
				int no = 001;
				String type = "狗' or '1' = '1";
				
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
		
		//批处理 区别PreparedStatement提供的批处理和普通Statement的区别
		


static class DbHelper
{
	private static final DbHelper INSTANCE = new DbHelper();
	
	private DbHelper(){}
	
	public static DbHelper getInstance()
	{
		return INSTANCE;
	}
	
	//获取数据库连接
			public  Connection getConnection()
			{
				//从文件conf/db.properties读取数据库配置信息
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
					System.out.println("开始连接...."); 
					Class.forName("oracle.jdbc.driver.OracleDriver");//加载JDBC驱动
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger"); //加载oracleURL和账户密码
					System.out.println("连接成功！");
				}catch(ClassNotFoundException e)
				{
					System.out.println("没有找到驱动类： oracle.jdbc.driver.OracleDriver!");
					return null;
				}catch(SQLException e)
				{
					System.out.println("连接失败！");
				}
				return conn;
			}
			
			//从文件读取数据库配置信息
			public Properties getDbConfig()
			{
				InputStream is = null;
				Properties props = new Properties();
				try
				{
					//读取当前程序classpath目录下的conf/db.properties，而此时的classpath为 "."
					is = DbHelper.class.getClassLoader().getResourceAsStream("conf/db.properties");
					props.load(is);
					System.out.println("成功读取配置文件！");
				}catch(FileNotFoundException e)
				{
					System.out.println("找不到配置文件！");
					return null;
				}catch(IOException e)
				{
					System.out.println("读取配置文件失败！");
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
			
			//关闭数据库连接
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
						System.out.println("断开连接！");
					}
				}
			}
			
			//关闭所有资源
			public void closeQuiely(Statement stat, ResultSet rs)
			{
				closeStatement(stat);
				closeResultSet(rs);
			}
			
			//关闭Statement
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
					
			//关闭结果集ResultSet
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
