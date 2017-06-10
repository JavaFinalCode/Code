public class Emp
{
	private String empno;
	
	private String ename;
	
	private double sal;
	
	
	private java.sql.Date hiredate;
	
	private String job;
	
	private int mgr;
	
	private double comm;
	
	private int deptno;
	
	public String getEmpno()
	{
		return empno;
	}
	
	public void setEmpno(String empno)
	{
		this.empno = empno;
	}
	
	public  String getEname()
	{
		return ename;
	}
	
	public void setEname(String ename)
	{
		this.ename = ename;
	}
	
	public double getSal()
	{
		return sal;
	}
	
	public void setSal(double sal)
	{
		this.sal = sal;
	}
	
	public java.sql.Date getHiredate()
	{
		return hiredate;
	}
	
	public void setHiredate(java.sql.Date hiredate)
	{
		this.hiredate = hiredate;
	}
	
	public String getJob()
	{
		return job;
	}
	
	public void setJob(String job)
	{
		this.job = job;
	}
	
	public int getMgr()
	{
		return mgr;
	}
	
	public void setMgr(int mgr)
	{
		this.mgr = mgr;
	}
	
	public double getComm()
	{
		return comm;
	}
	
	public void setComm(double comm)
	{
		this.comm =comm;
	}
	
	public int getDeptno()
	{
		return deptno;
	}
	public void  setDeptno(int deptno)
	{
		this.deptno = deptno;
	}
	
	@Override
	public String toString()
	{
		return "Emp[empno: " +empno + ",ename: " +ename +",sal: " +sal +",hiredate: " +hiredate +",job: " +job +",mgr: " +mgr +",comm: " +comm +",deptno: " +deptno +"]\n\n";
	}
}