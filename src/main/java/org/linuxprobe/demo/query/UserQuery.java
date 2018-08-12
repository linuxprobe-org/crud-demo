package org.linuxprobe.demo.query;

import org.linuxprobe.crud.core.annoatation.JoinColumn;
import org.linuxprobe.crud.core.annoatation.Search;
import org.linuxprobe.crud.core.query.BaseQuery;
import org.linuxprobe.crud.core.query.param.impl.BooleanParam;
import org.linuxprobe.crud.core.query.param.impl.DateParam;
import org.linuxprobe.crud.core.query.param.impl.NumberParam;
import org.linuxprobe.crud.core.query.param.impl.StringParam;
import org.linuxprobe.demo.model.User;
import lombok.Getter;
import lombok.Setter;

/** 查询用户表，继承BaseQuery可以实现排序和分页，不继承则不排序，不分页,BaseQuery已经自带一个id字段条件 */
@Search(User.class)
@Getter
@Setter
public class UserQuery extends BaseQuery {
	/** 使用StringParam类指定name是一个字符串类型字段 */
	private StringParam name;
	/** 使用NumberParam类指定age是一个数字类型字段,java类中,只要是Number类的子类的都这样写 */
	private NumberParam age;
	/** 使用BooleanParam类指定enable是一个布尔类型字段 */
	private BooleanParam enable;
	/** 使用DateParam类指定createTime是一个字符串类型字段 */
	private DateParam createTime;
	/** 枚举类型根据存储的是字符串还是序列号来确定使用StringParam还是NumberParam */
	/** 以上是所有支持字段的类型 */
	/*******************************************************************************************************/
	/** 关联查询 */
	/** 关联查询模式主表使用org_id字段进行关联，从表使用主键关联 */
	/** 可使用@JoinColumn注解自定义关联列名 */
	/** principal和value一样都是指定主表链接字段的，subordinate是指定从表链接字段的，principal和subordinate可以单独使用，并非要二者一起赋值 */
	/** 不指定赋值给哪个变量默认就是value,告诉程序链接查询时主表字段使用org_id字段 */
	@JoinColumn("org_id")
	private OrgQuery org;
	/** 一对多的关联查询，可以这样设置，主表使用id关联，从表使用user_id关联 */
	@JoinColumn(principal = "id", subordinate = "user_id")
	private RoleQuery role;
}
