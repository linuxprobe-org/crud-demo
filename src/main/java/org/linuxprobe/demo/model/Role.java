package org.linuxprobe.demo.model;

import org.linuxprobe.crud.core.annoatation.Entity;
import org.linuxprobe.crud.core.annoatation.PrimaryKey;
import org.linuxprobe.crud.core.annoatation.PrimaryKey.Strategy;
import org.linuxprobe.crud.core.annoatation.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table("role")
@Entity
public class Role {
	@PrimaryKey(Strategy.UUID)
	private String id;
	private String userId;
	/** 角色名称 */
	private String name;
}
