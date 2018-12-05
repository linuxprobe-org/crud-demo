package org.linuxprobe.demo;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.apache.ibatis.io.Resources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.linuxprobe.crud.mybatis.session.UniversalCrudSqlSession;
import org.linuxprobe.crud.mybatis.session.UniversalCrudSqlSessionFactory;
import org.linuxprobe.crud.mybatis.session.UniversalCrudSqlSessionFactoryBuilder;
import org.linuxprobe.demo.model.Book;
import org.linuxprobe.demo.model.User;
import org.linuxprobe.demo.query.UserQuery;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {
	UniversalCrudSqlSessionFactory sqlSessionFactory = null;
	UniversalCrudSqlSession sqlSession = null;
	{
		String resource = "mybatis-config.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		UniversalCrudSqlSessionFactoryBuilder sqlSessionFactoryBuilder = new UniversalCrudSqlSessionFactoryBuilder();
		sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);
		sqlSession = sqlSessionFactory.openSession();
	}

	@Test
	public void selectTest() {
		UserQuery userQuery = new UserQuery();
		List<User> users = this.sqlSession.universalSelect(userQuery, User.class);
		for (User user : users) {
			System.out.println(user);
		}
	}

	@Test
	public void insertTest() {
		Book book = new Book();
		book.setName("f234");
		this.sqlSession.insert(book);
		System.out.println("当前id:" + book.getIds());
		sqlSession.commit();
	}

	@Test
	public void barchInsertTest() {
		List<Book> books = new LinkedList<>();
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			Book book = new Book();
			book.setName("f" + rand.nextInt());
			book.setIds(rand.nextLong());
			books.add(book);
		}
		this.sqlSession.batchInsert(books);
		for (int i = 0; i < 10; i++) {
			System.out.println("当前id:" + books.get(i));
		}
		sqlSession.commit();
	}

	@Test
	public void deleteTest() {
		this.sqlSession.deleteByPrimaryKey(22, Book.class);
		sqlSession.commit();
	}

	@Test
	public void batchDeleteTest() {
		Set<Serializable> ids = new HashSet<>();
		ids.add(21);
		ids.add(24);
		this.sqlSession.batchDeleteByPrimaryKey(ids, Book.class);
		sqlSession.commit();
	}

	@Test
	public void deleteObjectTest() {
		Book book = new Book();
		book.setIds(25L);
		this.sqlSession.delete(book);
		sqlSession.commit();
	}

	@Test
	public void batchDeleteObjectTest() {
		Set<Book> books = new HashSet<>();
		Book book = new Book();
		book.setIds(25L);
		Book book2 = new Book();
		book2.setIds(26L);
		books.add(book2);
		books.add(book);
		this.sqlSession.batchDelete(books);
		sqlSession.commit();
	}
	
	/** 保存测试 */
	@Test
	public void saveTest() {
		User user = new User();
		user.setAge(11);
		user.setEnable(true);
		user.setName("张三");
		user.setCreateTime(new Date());
		sqlSession.insert(user);
		System.out.println("新增用户:" + user);
		sqlSession.commit();
	}
}
