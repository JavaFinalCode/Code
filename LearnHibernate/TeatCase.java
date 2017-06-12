package com.cheer.hibernate.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;

import com.cheer.hibernate.domain.Message;

public class TeatCase
{
	// 增
	@Test
	public void testPersist()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloWorldPU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		Message message = new Message();
		message.setText("Hello World!");

		Message message1 = new Message();
		message1.setText("Hello China");

		Message message2 = new Message();
		message2.setText("Hello KunShan");

		em.persist(message);
		em.persist(message1);
		em.persist(message2);

		tx.commit();
		em.close();

	}

	// 删
	@Test
	public void delectTest()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloWorldPU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		// 先找到要删除的
		Message message = em.find(Message.class , 2);
		// 删除
		em.remove(message);
		tx.commit();

		em.close();

	}

	// =======================查询
	/**
	 * find方法相当于Hibernate中的get方法， getReference方法相当于Hibernate中的load方法，具有延迟加载的作用；
	 * 当查找的对象不存在时，find的方法返回null值，
	 * 而getReference方法则抛出异常javax.persistence.EntityNotFoundException
	 */

	/**
	 * 查询byFind
	 */
	@Test
	public void findByFind()
	{

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("HelloWorldPU");
		EntityManager em = factory.createEntityManager();

		// Find方法相当于Hibernate中的get方法
		Message message = em.find(Message.class, 1);
		Message message2 = em.find(Message.class, 3);
		
		System.out.println(message.getText());
		System.out.println(message2.getText());
		
		em.close();
		factory.close();
	}

	/**
	 * 查询ByReference
	 */
	@Test
	public void findByReference()
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("HelloWorldPU");
		EntityManager em = factory.createEntityManager();

		// getReference方法相当于Hibername中的load方法，具有延迟加载的功能
		Message message = em.getReference(Message.class, 1);
		Message message2 = em.getReference(Message.class, 3);
		System.out.println(message.getText());
		System.out.println(message2.getText());
		em.close();
		factory.close();
	}

	/**
	 * 更新
	 */
	@Test
	public void updateMessage()
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("HelloWorldPU");
		EntityManager em = factory.createEntityManager();

		em.getTransaction().begin();
		Message message = em.find(Message.class, 3);
		message.setText("Hello CaoMing");
		// 相当于hibernate将游离状态下的数据更新到数据库中
		em.getTransaction().commit();

		em.close();
		factory.close();
	}

	/**
	 * 更新 将游离状态下的数据更新到数据库中
	 */
	@Test
	public void updateMessageByMerge()
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("HelloWorldPU");
		EntityManager em = factory.createEntityManager();

		em.getTransaction().begin();
		Message message = em.find(Message.class, 3);
		// 把实体管理器中的实体变成游离状态
		em.clear();
		message.setText("hello cheer");
		// 把游离状态下的更新同步到数据库中
		em.merge(message);
		em.getTransaction().commit();

		em.close();
		factory.close();
	}

	// 查（） 改
	@Test
	public void testGet()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloWorldPU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		@SuppressWarnings("unchecked")
		// 查
		List<Message> messages = em.createQuery("select m from Message m").getResultList();

		for (Message a : messages)
		{
			System.out.println(a);
		}

		Assert.assertEquals(2, messages.size());
		Assert.assertEquals("Hello World!", messages.get(0).getText());

		// 改
		messages.get(0).setText("Take me to your leader!");

		tx.commit();

		em.close();
	}

}
