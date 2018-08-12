package org.linuxprobe.demo.model;

import org.linuxprobe.crud.core.annoatation.PrimaryKey;
import org.linuxprobe.crud.core.annoatation.Table;
import lombok.Getter;
import lombok.Setter;

@Table("org")
@Getter
@Setter
public class Org {
	@PrimaryKey
	private String id;
	private String name;
}
