package org.linuxprobe.demo;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import org.apache.ibatis.io.Resources;
import org.linuxprobe.crud.core.sql.generator.SqlGenerator;
import org.linuxprobe.crud.core.sql.generator.SqlGenerator.DataBaseType;
import org.linuxprobe.crud.mybatis.session.UniversalCrudSqlSession;
import org.linuxprobe.crud.mybatis.session.UniversalCrudSqlSessionFactory;
import org.linuxprobe.crud.mybatis.session.UniversalCrudSqlSessionFactoryBuilder;
import org.linuxprobe.demo.model.User;
import org.linuxprobe.demo.model.User.Type;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MybatisTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public MybatisTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(MybatisTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		SqlGenerator.setDataBaseType(DataBaseType.Mysql);
		String resource = "mybatis-config.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UniversalCrudSqlSessionFactoryBuilder sqlSessionFactoryBuilder = new UniversalCrudSqlSessionFactoryBuilder();
		UniversalCrudSqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);
		UniversalCrudSqlSession sqlSession = sqlSessionFactory.openSession();
		User user = new User();
		user.setAge(12);
		user.setCreateTime(new Date());
		user.setEnable(true);
		user.setName("sdf");
		user.setType(Type.Student);
		user.setOrgId("2");
		sqlSession.insert(user);
		assertTrue(true);
	}
}
