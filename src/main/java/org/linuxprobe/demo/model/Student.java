package org.linuxprobe.demo.model;

import java.util.Date;

import org.linuxprobe.crud.core.annoatation.Column;
import org.linuxprobe.crud.core.annoatation.DateHandler;
import org.linuxprobe.crud.core.annoatation.DateHandler.DateCustomerType;
import org.linuxprobe.crud.core.annoatation.Entity;
import org.linuxprobe.crud.core.annoatation.PrimaryKey;
import org.linuxprobe.crud.core.annoatation.PrimaryKey.Strategy;
import org.linuxprobe.crud.core.annoatation.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table("student")
@Getter
@Setter
public class Student {
	@Column(notNull = false, updateIgnore = true)
	@DateHandler(customerType = DateCustomerType.Timestamp)
	private Date createDate = new Date();
	@PrimaryKey(Strategy.UUID)
	private String id = "sdf";
	@Column(value = "age_s", notNull = true)
	private int age;
	@Column(notNull = true)
	private Integer Iage = 1;
}
