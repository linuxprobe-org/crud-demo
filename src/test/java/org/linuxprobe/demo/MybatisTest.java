package org.linuxprobe.demo;

import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
		System.out.println("当前id:" + book.getId());
	}

	@Test
	public void barchInsertTest() {
		List<Book> books = new LinkedList<>();
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			Book book = new Book();
			book.setName("f" + rand.nextInt());
			books.add(book);
		}

		this.sqlSession.batchInsert(books);
		for (int i = 0; i < 10; i++) {
			System.out.println("当前id:" + books.get(i));
		}
	}
}
