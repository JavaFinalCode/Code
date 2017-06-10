package com.cheer.dao.test;

import com.cheer.dao.impl.PetDaoImpl;
import com.cheer.dao.PetDao;
import com.cheer.domain.Pet;
import com.cheer.util.DbHelper;

import java.sql.Connection;
import java.util.List;

// 单元测试
import org.junit.Test;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Ignore;

// log4j
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PetDaoTestCase
{
	// 日志
	private static final Logger LOGGER = LogManager.getLogger(PetDaoTestCase.class);

	private static Connection conn = null;

	@BeforeClass
	public static void init()
	{
		conn = DbHelper.getInstance().getConnection();

		if (null == conn)
		{
			LOGGER.warn("未建立数据库连接，系统退出！");
			System.exit(0);
		}
	}

	@AfterClass
	public static void destory()
	{
		DbHelper.getInstance().closeConnection(conn);
	}

	@Test
	public void a_testSave()
	{
		PetDao petDao = new PetDaoImpl(conn);

		Pet pet = new Pet(1, "阿黃");

		int result  = petDao.save(pet);

		Assert.assertEquals(1, result);
	}

	@Test
	//@Ignore
	public void x_testDelete()
	{
		PetDao petDao = new PetDaoImpl(conn);

		Pet pet = new Pet(1, "阿黃");

		int result  = petDao.delete(pet); 

		Assert.assertEquals(1, result);
	}

	@Test
	public void  b_testUpdate()
	{
		PetDao petDao = new PetDaoImpl(conn);

		Pet pet = new Pet(1, "阿三");

		int result  = petDao.update(pet); 

		Assert.assertEquals(1, result);
	}

	@Test
	public void c_testFind()
	{
		PetDao petDao = new PetDaoImpl(conn);
		Pet pet = petDao.find(1);

		Assert.assertNotNull(pet);
	}

	@Test
	public void d_findAll()
	{
		PetDao petDao = new PetDaoImpl(conn);
		List<Pet> pets = petDao.findAll();

		Assert.assertEquals(1, pets.size());
	}
}