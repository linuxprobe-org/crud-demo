package org.linuxprobe.demo.model;

import org.linuxprobe.crud.core.annoatation.PrimaryKey;
import org.linuxprobe.crud.core.annoatation.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table("role")
public class Role {
	@PrimaryKey
	private String id;
	private String userId;
	/** 角色名称 */
	private String name;
}
