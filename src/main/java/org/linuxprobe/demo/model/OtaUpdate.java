package org.linuxprobe.demo.model;

import org.linuxprobe.crud.core.annoatation.Entity;
import org.linuxprobe.crud.core.annoatation.PrimaryKey;
import org.linuxprobe.crud.core.annoatation.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table("ota_update")
@Getter
@Setter
@ToString
@Entity
public class OtaUpdate {
	@PrimaryKey
	private String id;
	private Integer urlId;
	private String httpurl;
	private String name;
	private Double size;
}
