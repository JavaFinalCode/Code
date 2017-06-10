package com.cheer.dao.impl;

import com.cheer.dao.PetDao;
import com.cheer.domain.Pet;
import com.cheer.util.DbHelper;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;

// DBUtils
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

// log4j
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class PetDaoImpl implements PetDao
{
	// 日志
	private static final Logger LOGGER = LogManager.getLogger(PetDaoImpl.class);

	private Connection conn = null;

	public PetDaoImpl(){}

	public PetDaoImpl(Connection conn)
	{
		this.conn = conn;
	}

	public int save(Pet pet)
	{
		int count = -1;
		PreparedStatement stat = null;
		try
		{
			String sql = "insert into pet(id, name) values(?, ?)";
			stat = conn.prepareStatement(sql);
			stat.setInt(1, pet.getId());
			stat.setString(2, pet.getName());
			count = stat.executeUpdate();
			LOGGER.debug("插入{}行记录！", count);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// 关闭资源
			DbHelper.getInstance().closeQuietly(stat, null);
		}	
		return count;	
	}

	public int delete(Pet pet)
	{
		int count = -1;
		PreparedStatement stat = null;
		try
		{
			String sql = "delete from pet where id = ?";
			stat = conn.prepareStatement(sql);
			stat.setInt(1, pet.getId());
			count = stat.executeUpdate();
			LOGGER.debug("删除{}行记录！", count);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// 关闭资源
			DbHelper.getInstance().closeQuietly(stat, null);
		}
		return count;		
	}

	public int update(Pet pet)
	{
		int count = -1;
		PreparedStatement stat = null;
		try
		{
			String sql = "update pet set name = ? where id = ?";
			stat = conn.prepareStatement(sql);
			stat.setString(1, pet.getName());
			stat.setInt(2, pet.getId());
			count = stat.executeUpdate();
			LOGGER.debug("更新{}行记录！", count);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// 关闭资源
			DbHelper.getInstance().closeQuietly(stat, null);
		}
		return count;
	}

	public Pet find(int id)
	{
		ResultSetHandler<Pet> handler = new BeanHandler<Pet>(Pet.class);
		QueryRunner runner = new QueryRunner();

		Pet pet = null;
		try
		{
			pet = runner.query(conn, "select * from pet where id = ?", handler, id);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		if (null == pet)
		{
			LOGGER.info("没有查询到记录！");
		}
		LOGGER.debug("查询到id为{}的记录！", id);
		return pet;
	}

	public List<Pet> findAll()
	{
		ResultSetHandler<List<Pet>> handler = new BeanListHandler<Pet>(Pet.class);
		QueryRunner runner = new QueryRunner();

		List<Pet> pets = null;
		try
		{
			pets = runner.query(conn, "select * from Pet", handler);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		LOGGER.debug("查询到{}记录", pets.size());
		if(null == pets)
		{
			return new ArrayList<Pet>(0);
		}
		return pets;
	}
}