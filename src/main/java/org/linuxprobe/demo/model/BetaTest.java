package org.linuxprobe.demo.model;

import java.util.Date;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;

import org.linuxprobe.crud.core.annoatation.DateHandler;
import org.linuxprobe.crud.core.annoatation.DateHandler.DateCustomerType;
import org.linuxprobe.crud.core.annoatation.Entity;
import org.linuxprobe.crud.core.annoatation.PrimaryKey;
import org.linuxprobe.crud.core.annoatation.PrimaryKey.Strategy;
import org.linuxprobe.crud.core.annoatation.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table("beta_test")
@Getter
@Setter
public class BetaTest {
	@PrimaryKey(Strategy.NATIVE)
	private Long id;
	/**  */
	@DateHandler(customerType = DateCustomerType.Timestamp)
	@Past
	private Date createDate;
	@Min(1)
	private int age;
	private SerialBlob bin;
	private Type type;
	private boolean isenable;

	public static enum Type {
		A, B;
	}

	@Override
	public String toString() {
		try {
			return "BetaTest [id=" + id + ", createDate=" + createDate + ", age=" + age + ", bin="
					+ new String(bin.getBytes(1, (int) bin.length())) + ", type=" + type + ", isenable=" + isenable
					+ "]";
		} catch (SerialException e) {
			return null;
		}
	}

}
