package org.linuxprobe.demo;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.linuxprobe.crud.core.query.BaseQuery.JoinType;
import org.linuxprobe.crud.core.query.BaseQuery.Limit;
import org.linuxprobe.crud.core.query.param.BaseParam.Condition;
import org.linuxprobe.crud.core.query.param.BaseParam.Operator;
import org.linuxprobe.crud.core.query.param.impl.BooleanParam;
import org.linuxprobe.crud.core.query.param.impl.DateParam;
import org.linuxprobe.crud.core.query.param.impl.NumberParam;
import org.linuxprobe.crud.core.query.param.impl.StringParam;
import org.linuxprobe.crud.core.query.param.impl.StringParam.Fuzzt;
import org.linuxprobe.crud.mybatis.session.UniversalCrudSqlSession;
import org.linuxprobe.crud.mybatis.session.UniversalCrudSqlSessionFactory;
import org.linuxprobe.crud.mybatis.session.UniversalCrudSqlSessionFactoryBuilder;
import org.linuxprobe.demo.model.Book;
import org.linuxprobe.demo.model.Org;
import org.linuxprobe.demo.model.User;
import org.linuxprobe.demo.model.User.Type;
import org.linuxprobe.demo.query.OrgQuery;
import org.linuxprobe.demo.query.RoleQuery;
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

	/** 删除测试 */
	@Test
	public void deleteTest() {
		/** 类型删除 */
		this.sqlSession.deleteByPrimaryKey(22, Book.class);
		/** 类型批量删除 */
		Set<Serializable> ids = new HashSet<>();
		ids.add(21);
		ids.add(24);
		this.sqlSession.batchDeleteByPrimaryKey(ids, Book.class);
		/** 对象删除 */
		Book book = new Book();
		book.setIds(25L);
		this.sqlSession.delete(book);
		/** 对象批量删除 */
		Set<Book> books = new HashSet<>();
		Book book1 = new Book();
		book1.setIds(25L);
		Book book2 = new Book();
		book2.setIds(26L);
		books.add(book2);
		books.add(book1);
		this.sqlSession.batchDelete(books);
		/** 用户删除测试 */
		this.sqlSession.deleteByPrimaryKey("123", User.class);
		sqlSession.commit();
	}

	@Test
	public void insertTest() {
		try {
			Book newbook = new Book();
			newbook.setName("f234");
			this.sqlSession.insert(newbook);
			System.out.println("当前id:" + newbook.getIds());
			sqlSession.commit();

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

			User user = new User();
			user.setName("张三");
			user.setAge(11);
			user.setWeigth(50.01f);
			user.setHeight(165.0);
			user.setIdCard(53212819930708L);
			user.setEnable(true);
			user.setCreateTime(new Date());
			user.setOrgId("2");
			user.setType(Type.Student);
			sqlSession.insert(user);
			System.out.println("新增用户:" + user);

			List<User> users = new LinkedList<>();
			for (int i = 0; i < 10; i++) {
				User user1 = new User();
				user1.setName("张三");
				user1.setAge(11);
				user1.setWeigth(50.01f);
				user1.setHeight(165.0);
				user1.setIdCard(53212819930708L);
				user1.setEnable(true);
				user1.setCreateTime(new Date());
				user1.setOrgId("2");
				user1.setType(Type.Student);
				users.add(user1);
			}
			sqlSession.batchInsert(users);
			for (int i = 0; i < 10; i++) {
				System.out.println("新增用户:" + users.get(i));
			}
			sqlSession.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void selectTest() {
		try {
			UserQuery query = new UserQuery();
			query.setEnable(new BooleanParam(true));
			query.setCreateTime(new DateParam(Operator.isNotNull));
			/** 查询name是张三的用户 */
			StringParam nameParam = new StringParam();
			nameParam.setValue("张三");
			/** 可以设置Operator指定where条件的操作符=，!=,like等其它操作 */
			nameParam.setOperator(Operator.like);
			query.setName(nameParam);
			List<User> users = sqlSession.universalSelect(query, User.class);
			for (User user : users) {
				System.out.println(user);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/** 查询数量测试 */
	@Test
	public void selectCountTest() {
		UserQuery query = new UserQuery();
		/** 查询name是张三的用户 */
		StringParam nameParam = new StringParam();
		nameParam.setValue("张三");
		/** 可以设置Operator指定where条件的操作符=，!=,like等其它操作 */
		nameParam.setOperator(Operator.like);
		nameParam.setFuzzt(Fuzzt.Left);
		query.setName(nameParam);
		System.out.println("数量" + sqlSession.selectCount(query));
	}

	/** 使用setMultipart进行in查询 */
	@Test
	public void inSelectTest() {
		UserQuery query = new UserQuery();
		StringParam nameParam = new StringParam();
		/** 查询name是张三1和张三5的用户 */
		List<String> names = new LinkedList<>();
		names.add("张三1");
		names.add("张三5");
		nameParam.setMultiValues(names);
		/** 指定操作符in */
		nameParam.setOperator(Operator.notIn);
		query.setName(nameParam);
		List<User> users = sqlSession.universalSelect(query, User.class);
		for (User user : users) {
			System.out.println(user);
		}
	}

	/**
	 * 使用setLowerLimit和setUpperLimit进行between查询1到20岁的记录，并根据age倒排序,name正排序，分页取第一页前5条
	 */
	@Test
	public void betweenSelectTest() {
		UserQuery query = new UserQuery();
		NumberParam ageParam = new NumberParam();
		ageParam.setOperator(Operator.notBetween);
		/** 设置下限值 */
		ageParam.setMinValue(1);
		/** 设置上限值 */
		ageParam.setMaxValue(20);
		query.setAge(ageParam);
		/** 排序设置 */
		query.setOrder("age DESC,name");
		/** 分页设置 */
		query.setLimit(new Limit(2, 10));
		List<User> users = sqlSession.universalSelect(query, User.class);
		for (User user : users) {
			System.out.println(user);
		}
		/** 数量查询 */
		System.out.println("该条件下共有" + sqlSession.selectCount(query) + "条数据");
	}

	/** 在UserQuery添加OrgQuery类型变量为成员对象进行一对一的关联查询 */
	@Test
	public void oneToOneselectTest() {
		UserQuery query = new UserQuery();
		query.setName(new StringParam(Condition.or, Operator.like, "张三"));
		OrgQuery orgQuery = new OrgQuery();
		orgQuery.setJoinType(JoinType.CrossJoin);
		orgQuery.setName(new StringParam("综合部"));
		query.setOrg(orgQuery);
		RoleQuery roleQuery = new RoleQuery();
		roleQuery.setName(new StringParam("管理员"));
		// query.setRole(roleQuery);
		List<User> users = sqlSession.universalSelect(query, User.class);
		for (User user : users) {
			System.out.println(user);
		}
	}

	/** 一对多查询 */
	@Test
	public void oneToManylectTest() {
		UserQuery query = new UserQuery();
		RoleQuery roleQuery = new RoleQuery();
		roleQuery.setName(new StringParam("管理员"));
		/** 查询用户管理员角色的人 */
		query.setRole(roleQuery);
		List<User> users = sqlSession.universalSelect(query, User.class);
		for (User user : users) {
			System.out.println(user);
		}
	}

	/** sql查询 */
	@Test
	public void sqlSelectTest() {
		List<Map<String, Object>> result = sqlSession
				.selectBySql("select distinct o.* from org o left join user u on o.id = u.org_id order by o.id");
		for (Map<String, Object> temp : result) {
			System.out.println(temp);
		}
	}

	/** sql查询 */
	@Test
	public void sqlSelectTypeTest() {
		List<Org> result = sqlSession.selectBySql(
				"select distinct o.* from org o left join user u on o.id = u.org_id order by o.id", Org.class);
		for (Org temp : result) {
			System.out.println(temp.toString());
		}
	}

	/** sql查询 */
	@Test
	public void test() {
		UserQuery query = new UserQuery();
		query.setLimit(null);
		List<User> users = sqlSession.universalSelect(query, User.class);
		for (User user : users) {
			String uuid = user.getId().replaceAll("-", "");
			ByteBuffer buffer = ByteBuffer.wrap(uuid.getBytes(), 0, uuid.length());
			System.out.println(buffer.getShort());
		}
	}

	/** 字段全更新测试 */
	@Test
	public void globalUpdateTest() {
		User user = new User();
		user.setId("1eb04a92-262d-40b1-8daf-d70fc82cccaa");
		user.setAge(20);
		user.setEnable(true);
		user.setName("李四");
		user.setCreateTime(new Date());
		sqlSession.globalUpdate(user);
		System.out.println("更新用户:" + user);
	}

	/** 字段选择更新测试 */
	@Test
	public void localUpdateTest() {
		try {
			User user = new User();
			user.setId("e4f8e18b-a473-43f9-a544-890de7f3b424");
			user.setAge(50);
			sqlSession.localUpdate(user);
			System.out.println("更新用户:" + user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
