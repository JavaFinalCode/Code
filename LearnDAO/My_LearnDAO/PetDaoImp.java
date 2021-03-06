public PetDaoImp implements PetDao
{
	public int update(Pet pet)
	{
		PreparedStatement stat = null;
		ResultSet rs = null;
		try
		{
			String sql = "update master set loginid=0 where id=?;";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, pet.getId());
			result=pstmt.executeUpdate()
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbHelper.getInstance().closeQuietly(pstmt,rs);
		}		
	}
}

