package org.linuxprobe.demo.model;

import java.util.Date;
import org.linuxprobe.crud.core.annoatation.Column;
import org.linuxprobe.crud.core.annoatation.Entity;
import org.linuxprobe.crud.core.annoatation.Column.EnumHandler;
import org.linuxprobe.crud.core.annoatation.Column.LengthHandler;
import org.linuxprobe.crud.core.annoatation.PrimaryKey;
import org.linuxprobe.crud.core.annoatation.Table;
import org.linuxprobe.crud.core.annoatation.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table("user")
@Getter
@Setter
@ToString
@Entity
public class User {
	@PrimaryKey
	private String id;
	/** @Column指定长度，超过长度默认减去超过长度，可设置lengthHandler=LengthHandler.ThrowException抛出异常 */
	@Column(length = 32, lengthHandler = LengthHandler.ThrowException)
	private String name;
	private Integer age;
	private Boolean enable;
	/** @Column updateIgnore=true表示进行更新操作时，这个字段不更新 */
	@Column(updateIgnore = true)
	private Date createTime;
	/** @Column指定数据库字段 */
	@Column("org_id")
	private String orgId;
	
	/** @Transient表示test字段不进行持久化 */
	@Transient
	private String test;

	/** @Column(enumHandler=EnumHandler.Name)指定枚举存名称 */
	/** @Column(enumHandler=EnumHandler.Ordinal)指定枚举存序列 */
	/** enumHandler默认是序列 */
	@Transient
	@Column(enumHandler = EnumHandler.Ordinal)
	private Type type;

	public static enum Type {
		Teacher, Student
	}
}
