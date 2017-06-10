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
import java.util.List;

//DBUtils
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.Handlers.BeanHandler;
import org.apache.commons.dbutils.Handlers.BeanListHandler;

//白盒测试
import org.junit.Test;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Ignore;

public class LearnJdbcTestDog
{
	//白盒测试下的起始与结束
	//起始-->得到连接connection
	//中间各个测试方法;
	//结束connection关闭资源;避免中间方法不停开关资源
	
	private static Connection conn=null;

//测试前准备工作，建立数据库连接	
	@BeforeClass
	public static void init()
	{
		//得到实例，通过实例调取连接对象，好处：以下测试公用同一对象，节省资源
		conn=DbHelper.getInstance().getConnection();
		
		if(null==conn)
		{
			System.out.print("未建立数据库连接，系统退出");
			System.exit(0);
		}
	}	
	
	//关闭连接
	@AfterClass
	public static void destory()
	{
		DbHelper.getInstance().closeConnection(conn);
	}
	
	//插入
	@Test
	@Ignore
	public void a_insert()
	{
		Statement stat=null;
		
		try
		{
			String sql="insert into dog values(3,'聪聪'，5,'母'，'哈士奇',2)";
			stat=conn.createStatement();
			int count=stat.executeUpdate(sql);
			
			//预期
			Assert.assertEquals(1,count);
			
			System.out.println("成功插入"+count+"行数据！");
		}
		
		catch(SQLException e)
		{
			System.out.println("插入失败");
			e.printStackTrace();
		}
		
		finally
		{
			DbHelper.getInstance().closeAll(stat,null);
		}
	}
	
	//查询
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
			
			//列数目
			int a=rsmd.getColumnCount();
			//获取列名
			for(int i=1;i<=a;i++)
			{
				System.out.print(rsmd.getColumnName(i)+"\t");
			}
			System.out.print("\n");
			
			//获取列值
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
	
	//更改
	@Test
	@Ignore
	public void c_update()
	{
		Statement stat=null;
		
		try
		{
			String sql="update master set mname='王思聪' where mno=2";
			stat=conn.createStatement();
			int count = stat.executeUpdate(sql);
			System.out.println("成功修改"+count+"行数据！");
			Assert.assertEquals(1,count);	
		}
		catch(SQLException e)
		{
			System.out.println("修改失败！");
			e.printStackTrace();
		}
		finally
		{
			DbHelper.getInstance().closeAll(stat,null);
		}		
	}
	
	//删除
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
			System.out.println("成功删除"+count+"行数据！");
			Assert.assertEquals(1,count);	
		}
		catch(SQLException e)
		{
			System.out.println("删除失败！");
			e.printStackTrace();
		}
		finally
		{
			DbHelper.getInstance().closeAll(stat,null);
		}		
	}
	
	//具有sql注入安全缺陷的写法,用于登入
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
		  
		  //正常数据OK
		  //mno=2;
		  //mname="王思聪";
		  
		  //恶意数据，构造SQL注入
      mno=2;
		  mname=" hacker' or '1'='1 ";
		  
		  //select * from master where mno=2 and ename='王思聪';
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
			System.out.print("查询失败");
			e.printStackTrace();
		}
		finally
		{
			DbHelper.getInstance().closeAll(stat,rs);
		}		
	}
	
	//预处理
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
		  
		  //正常数据OK
		  //mno=2;
		  //mname="王思聪";
		  
		  //恶意数据，构造SQL注入
      mno=2;
		  mname=" hacker' or '1'='1 ";
		  
		  //select * from master where mno=2 and ename='王思聪';
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
			System.out.print("查询失败");
			e.printStackTrace();
		}
		finally
		{
			DbHelper.getInstance().closeAll(pstat,rs);
		}		
	}
	
	//批处理 Statement-->void addBatch(String sql),addBatch(String sql)..--> int[] excuteBatch()-->throw SQLException
  //优点：可以添加多条不同Sql语句
  //缺点：性能较差
	@Test
	public void g_executeBathWithStat()
	{
		Statement stat=null;
		try
		{	
			String sql="insert into dog values(4,'聪聪'，2,'母'，'萨摩耶',3)";
			String sql1="update master set mname='赵四' where mno=3";
			
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
				System.out.println("成功回滚");
			}
			catch(SQLException e1)
			{
				System.out.println("未能成功回滚");
			}
		}
		finally
		{
			DbHelper.getInstance().closeAll(stat,null);
		}
	}

//使用第三方的api apache的DBUtils
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

	
	
	//批处理 PreparedStatement-->setInt(?,?),setString(?,?)..-->addBatch()-->int[] excuteBatch()
  //优点：性能高，安全
  //缺点：只能执行一条sql语句
	@Test
	public void h_executeBathprestatment()
	{
		PreparedStatement pstat=null;
		try
		{	
			String sql="insert into master values(?,?)";
			pstat=conn.prepareStatement(sql);
			pstat.setInt(1,4);
			pstat.setString(2,"王五");
			pstat.addBatch();
			pstat.setInt(1,5);
			pstat.setString(2,"钱三");			
			pstat.addBatch();
			pstat.executeBatch();
			
			conn.commit();
			System.out.println("批处理2成功");
		}
		catch(SQLException e)
		{
			
			e.printStackTrace();
			
			//出现异常则事务回滚，防止数据受破坏
			try
			{
				conn.rollback();
				System.out.println("成功回滚");
			}
			catch(SQLException e1)
			{
				System.out.println("未能成功回滚");
			}
		}
		finally
		{
			System.out.println("*********************批处理2prepare");
			DbHelper.getInstance().closeAll(pstat,null);
		}
	}			
}


//单例类作用
//创建connection连接，关闭资源
//	          1.创建私有的静态常量实例
//						2.构造方法私有化
//						3.提供静态获取实例方法
//						4.添加普通方法
class DbHelper
{
	//1.创建私有的静态常量实例
	private static final DbHelper INSTANCE=new DbHelper();
	
	//2.构造方法私有化
	private DbHelper(){}
	
	//3.提供静态获取实例方法
	public static DbHelper getInstance()
	{
		return INSTANCE;
	}
	
	//4.添加普通方法
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
			//加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//创建连接对象
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


				