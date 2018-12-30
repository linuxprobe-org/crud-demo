package org.linuxprobe.demo.model;

import java.util.Date;

import org.linuxprobe.crud.core.annoatation.Column;
import org.linuxprobe.crud.core.annoatation.Entity;
import org.linuxprobe.crud.core.annoatation.OneToOne;
import org.linuxprobe.crud.core.annoatation.PrimaryKey;
import org.linuxprobe.crud.core.annoatation.PrimaryKey.Strategy;
import org.linuxprobe.crud.core.annoatation.Table;
import org.linuxprobe.crud.core.annoatation.Transient;
import org.linuxprobe.crud.core.annoatation.UpdateIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table("user")
public class User {
	@PrimaryKey(Strategy.UUID)
	private String id;
	/** @Column指定长度，超过长度默认减去超过长度，可设置lengthHandler=LengthHandler.ThrowException抛出异常 */
	private String name;
	private Integer age;
	private Float weigth;
	private Double height;
	private Long idCard;
	private Boolean enable;
	/** @Column updateIgnore=true表示进行更新操作时，这个字段不更新 */
	@UpdateIgnore
	private Date createTime;
	/** @Column指定数据库字段 */
	@Column("org_id")
	private String orgId;

	/** @Column(enumHandler=EnumHandler.Name)指定枚举存名称 */
	/** @Column(enumHandler=EnumHandler.Ordinal)指定枚举存序列 */
	/** enumHandler默认是序列 */
	private Type type;

	/** @Transient表示test字段不进行持久化 */
	@Transient
	private String test;

	public static enum Type {
		Teacher, Student
	}

	@OneToOne("org_id")
	private Org org;
}
