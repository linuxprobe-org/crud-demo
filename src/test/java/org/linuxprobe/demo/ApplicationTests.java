package org.linuxprobe.demo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.linuxprobe.crud.core.query.BaseQuery.Limit;
import org.linuxprobe.crud.core.query.param.QueryParam.Operator;
import org.linuxprobe.crud.core.query.param.impl.NumberParam;
import org.linuxprobe.crud.core.query.param.impl.StringParam;
import org.linuxprobe.crud.service.UniversalService;
import org.linuxprobe.demo.model.Org;
import org.linuxprobe.demo.model.User;
import org.linuxprobe.demo.query.OrgQuery;
import org.linuxprobe.demo.query.RoleQuery;
import org.linuxprobe.demo.query.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	@Autowired
	private UniversalService service;

	/** 保存测试 */
	@Test
	public void saveTest() {
		User user = new User();
		user.setAge(11);
		user.setEnable(true);
		user.setName("张三");
		user.setCreateTime(new Date());
		service.save(user);
		System.out.println("新增用户:" + user);
	}

	/** 批量保存测试 */
	@Test
	public void batchSaveTest() {
		List<User> users = new LinkedList<>();
		for (int i = 0; i < 20; i++) {
			User user = new User();
			user.setAge(11 + i);
			user.setEnable(i % 2 == 0 ? true : false);
			user.setName("张三" + i);
			user.setCreateTime(new Date());
			users.add(user);
		}
		service.batchSave(users);
		for (User user : users) {
			System.out.println("新增用户id:" + user);
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
		service.globalUpdate(user);
		System.out.println("更新用户:" + user);
	}

	/** 字段选择更新测试 */
	@Test
	public void localUpdateTest() {
		User user = new User();
		user.setId("e4f8e18b-a473-43f9-a544-890de7f3b424");
		user.setAge(50);
		service.localUpdate(user);
		System.out.println("更新用户:" + user);
	}

	/** 删除测试 */
	@Test
	public void deleteTest() {
		User user = new User();
		user.setId("8f8abe8c-1985-4423-b0e2-79cca0afc3f0");
		service.remove(user);
		System.out.println("删除用户:" + user);
		/** 也可使用以下接口删除数据 */
		// service.removeByPrimaryKey("id", User.class);
	}

	/** 批量删除测试 */
	@Test
	public void batchDeleteTest() {
		LinkedList<User> users = new LinkedList<>();
		User user1 = new User();
		user1.setId("cd4b5c4e-a517-4c28-afea-bd1b4f3577e1");
		users.add(user1);
		User user2 = new User();
		user2.setId("ceedb8cf-f30c-4da3-b594-ee6d077e8826");
		users.add(user2);
		service.batchRemove(users);
		/** 也可使用以下接口删除数据 */
		// service.batchRemoveByPrimaryKey(new LinkedList<String>(),
		// User.class);
	}

	/** 使用setValue进行where条件是单值的查询例如=,!=,kike ,is null */
	@Test
	public void selectTest() {
		UserQuery query = new UserQuery();
		/** 查询name是张三的用户 */
		StringParam nameParam = new StringParam();
		nameParam.setValue("张三");
		/** 可以设置Operator指定where条件的操作符=，!=,like等其它操作 */
		nameParam.setOperator(Operator.like);
		query.setName(nameParam);
		List<User> users = service.universalSelect(query, User.class);
		for (User user : users) {
			System.out.println(user);
		}
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
		nameParam.setMultipart(names);
		/** 指定操作符in */
		nameParam.setOperator(Operator.in);
		query.setName(nameParam);
		List<User> users = service.universalSelect(query, User.class);
		for (User user : users) {
			System.out.println(user);
		}
	}

	/** 使用setLowerLimit和setUpperLimit进行between查询1到20岁的记录，并根据age倒排序,name正排序，分页取第一页前5条 */
	@Test
	public void betweenSelectTest() {
		UserQuery query = new UserQuery();
		NumberParam ageParam = new NumberParam();
		ageParam.setOperator(Operator.between);
		/** 设置下限值 */
		ageParam.setLowerLimit(1);
		/** 设置上限值 */
		ageParam.setUpperLimit(20);
		query.setAge(ageParam);
		/** 排序设置 */
		query.setOrder("age desc,name");
		/** 分页设置 */
		query.setLimit(new Limit(1, 5));
		List<User> users = service.universalSelect(query, User.class);
		for (User user : users) {
			System.out.println(user);
		}
		/** 数量查询 */
		System.out.println("该条件下共有" + service.selectCount(query) + "条数据");
	}

	/** 在UserQuery添加OrgQuery类型变量为成员对象进行一对一的关联查询 */
	@Test
	public void oneToOneselectTest() {
		UserQuery query = new UserQuery();
		OrgQuery orgQuery = new OrgQuery();
		orgQuery.setName(new StringParam("综合部"));
		query.setOrg(orgQuery);
		List<User> users = service.universalSelect(query, User.class);
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
		List<User> users = service.universalSelect(query, User.class);
		for (User user : users) {
			System.out.println(user);
		}
	}

	/** sql查询 */
	@Test
	public void sqlSelectTest() {
		List<Map<String, Object>> result = service
				.selectBySql("select distinct o.* from org o left join user u on o.id = u.org_id order by o.id");
		for (Map<String, Object> temp : result) {
			System.out.println(temp);
		}
	}

	/** sql查询 */
	@Test
	public void sqlSelectTypeTest() {
		List<Org> result = service.selectBySql(
				"select distinct o.* from org o left join user u on o.id = u.org_id order by o.id", Org.class);
		for (Org temp : result) {
			System.out.println(temp.toString());
		}
	}
}
