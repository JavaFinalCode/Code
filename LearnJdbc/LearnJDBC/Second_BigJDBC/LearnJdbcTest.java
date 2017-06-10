//IO流
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

//配置文件
import java.util.Properties;

//JDBC
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;

//白盒测试
import org.junit.Test;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Ignore;



//通用执行语句增删改查均可：boolean execute(String sql) throws SQLException
//查询语句：ResultSet executeQuery(String sql) throws SQLException
//增加，删除，修改均用：int executeUpdate(String sql) throws SQLException
//获取元数据 ResultSetMetaData getMetaData() throws SQLException
//预编译：PreparedStatement 获取方法prepareStatement(String sql) throws SQLException
//批量处理 普通批处理：void addBatch(String sql),addBatch(String sql)..--> int[] excuteBatch()-->throw SQLException
//  			 预编译批处理：setInt(?,?),setString(?,?)..-->addBatch()-->int[] excuteBatch()

//事务  void rollback() throws SQLException
//   		void commit() throws SQLException

//单元测试junit 依赖的jar包
//junit-4.12.jar   hamcrest-core-1.3.jar  hamcrest-library-1.3.jar
//运行方法  java  org.junit.runner.JUnitCore classname
//方法执行顺序按照方法名的字典排序

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
			System.out.print("未建立数据库连接，系统退出");
			System.exit(0);
		}
	}
	
	@AfterClass
	public static void destory()
	{
		DbHelper.getInstance().closeConnection(conn);
	}
	
	//查询
	@Test
	@Ignore//测试时忽略该方法
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
			//获取列名
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
				//通过列在表中的序号（从1开始）取数据
				System.out.print(rs.getString(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				//通过列名方式取数据
				System.out.print(rs.getString(3)+"\n");*/
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("*********************查询");
			DbHelper.getInstance().closeAll(stat,rs);
		}
	}
	
	@Test
	@Ignore//测试时忽略该方法
	//插入数据
	public void a_insert()
	{
		Statement stat=null;
		try
		{
			String sql="insert into deptcopy values(60,'软件工程师','昆山其然')";	
			stat=conn.createStatement();
			int count=stat.executeUpdate(sql);
			System.out.println("插入"+count+"行数据！");
			
			//断言count为1，若不是，则测试不通过
			Assert.assertEquals(1,count);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("*********************插入");
			DbHelper.getInstance().closeAll(stat,null);
		}
	}
	
	@Test
	@Ignore//测试时忽略该方法
	//修改数据
	public void c_update()
	{
		Statement stat=null;
		try
		{
			String sql="update deptcopy set dname='助理' where deptno=50";	
			stat=conn.createStatement();
			int count=stat.executeUpdate(sql);
			System.out.println("修改"+count+"行数据！");
			
			//断言count为1，若不是，则测试不通过
			Assert.assertEquals(1,count);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//关闭资源
			System.out.println("*********************修改");
			DbHelper.getInstance().closeAll(stat,null);
		}		
	}
	
	@Test
	@Ignore//测试时忽略该方法
	//删除数据
	public void d_delete()
	{
		Statement stat=null;
		try
		{
			String sql="delete from deptcopy where deptno=60";	
			stat=conn.createStatement();
			int count=stat.executeUpdate(sql);
			System.out.println("删除"+count+"行数据！");
			
			//断言count为1，若不是，则测试不通过
			Assert.assertEquals(1,count);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//关闭资源
			System.out.println("*********************删除");
			DbHelper.getInstance().closeAll(stat,null);
		}		
	}
	
	

	//预处理
  @Test
  //@Ignore//测试时忽略该方法
  public void f_securityLogin()
	{		
		PreparedStatement pstat=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;
		try
		{	
			int deptno=0;
		  String dname=null;
		  
		  //正常数据OK
		  deptno=30;
		  dname="SALES";
		  
		  //恶意数据，构造SQL注入
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
			System.out.println("*********************预处理");
			DbHelper.getInstance().closeAll(pstat,rs);
		}
	}
	
	//具有sql注入安全缺陷的写法
  @Test
  @Ignore//测试时忽略该方法
  public void e_usafeLogin()
	{
		Statement stat=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;

		try
		{	
			int deptno=0;
		  String dname=null;
		  
		  //正常数据OK
		  //deptno=30;
		  //dname="SALES";
		  
		  //恶意数据，构造SQL注入
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
			System.out.println("*********************具有sql注入安全缺陷的写法");
			DbHelper.getInstance().closeAll(stat,rs);
		}
	}	
	
	//批处理 Statement-->void addBatch(String sql),addBatch(String sql)..--> int[] excuteBatch()-->throw SQLException
  //优点：可以添加多条不同Sql语句
  //缺点：性能较差
	@Test
	//@Ignore//测试时忽略该方法
	public void g_executeBathWithStat()
	{
		Statement stat=null;
		try
		{	
			String sql="insert into deptcopy values(70,'软件工程师','大内')";
			String sql1="insert into deptcopy values(80,'软件工程师','007')";
			
			//设置事务提交（默认自动提交）
			conn.setAutoCommit(false);
			
			stat=conn.createStatement();
			stat.addBatch(sql);
			stat.addBatch(sql1);
			//批量执行
			stat.executeBatch();
			
			stat.clearBatch();
			
			//手动提交事务
			conn.commit();
			System.out.println("批处理成功");
			
			//断言，如果相等则测试通过
			Assert.assertEquals(1,1);
		}
		catch(SQLException e)
		{
			
			e.printStackTrace();
			
			//出现异常则事务回滚，防止数据受破坏
			try
			{
				conn.rollback();
				System.out.println("未成功回滚");
			}
			catch(SQLException e1)
			{
				System.out.println("未能成功回滚");
			}
		}
		finally
		{
			System.out.println("*********************批处理");
			DbHelper.getInstance().closeAll(stat,null);
		}
	}
	
	//批处理 PreparedStatement-->setInt(?,?),setString(?,?)..-->addBatch()-->int[] excuteBatch()
  //优点：性能高，安全
  //缺点：只能执行一条sql语句
	@Test
	@Ignore//测试时忽略该方法
	public void h_executeBathprestatment()
	{
		PreparedStatement pstat=null;
		try
		{	
			String sql="insert into deptcopy values(?,?,?)";
			pstat=conn.prepareStatement(sql);
			pstat.setInt(1,90);
			pstat.setString(2,"'医生'");
			pstat.setString(3,"'上海'");
			pstat.addBatch();
			pstat.setInt(1,92);
			pstat.setString(2,"'老师'");
			pstat.setString(3,"'苏州'");			
			pstat.addBatch();
			pstat.executeBatch();
			System.out.println("批处理2成功");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("*********************批处理2prepare");
			DbHelper.getInstance().closeAll(pstat,null);
		}
	}
}


//单例模式（Singleton）创建数据库连接
//实现方法（恶汉式）
//	          1.创建私有的静态常量实例
//						2.构造方法私有化
//						3.提供静态获取实例方法
//						4.添加普通方法
//优点
//其他实现方式：懒汉式、考虑线程安全方式多种方式

class DbHelper
{
	private static final DbHelper INSTANCE=new DbHelper();
	
	private DbHelper(){}
	
	public static DbHelper getInstance()
	{
		return INSTANCE;
	}
	
	//获取数据库连接
	public Connection getConnection()
	{
		//从文件conf/db.properties读取数据库配置信息
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
			System.out.println("开始连接。。。");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,userName,password);
			System.out.println("连接成功。。。");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("没有找到驱动类：oracle.jdbc.driver.OracleDriver！");
			return null;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("连接失败。。。");
			return null;
		}
		return conn;
		
	}
	
	//从文件读取数据库配置信息
	public Properties getDbconfig()
	{
		InputStream in=null;
		Properties props=new Properties();
		
		try
		{
			//读取当前程序classPath目录下的conf/db.properties,就是和javawen文件放同一目录放在
			in=DbHelper.class.getClassLoader().getResourceAsStream("conf/db.properties");
			props.load(in);
			System.out.println("成功读取配置文件");
		}
		catch(FileNotFoundException E)
		{
			System.out.println("找不到配置文件");
			return null;
		}
		catch(IOException e)
		{
			System.out.println("读取配置文件失败");
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
	
	
	
	//关闭数据库连接
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
				System.out.println("断开连接！");
			}
		}
	}
	
//关闭所有资源
	public void closeAll(Statement stat,ResultSet rs)
	{
		closeStatement(stat);
		closeResultSet(rs);
	}

	//关闭数据库连接 Statement
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
	
	
	//关闭数据库连接 ResultSet
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