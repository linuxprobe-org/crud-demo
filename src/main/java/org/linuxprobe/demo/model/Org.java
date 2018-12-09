package org.linuxprobe.demo.model;

import org.linuxprobe.crud.core.annoatation.Entity;
import org.linuxprobe.crud.core.annoatation.PrimaryKey;
import org.linuxprobe.crud.core.annoatation.Table;
import org.linuxprobe.crud.core.annoatation.PrimaryKey.Strategy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table("org")
@Getter
@Setter
@ToString
@Entity
public class Org  {
	@PrimaryKey(Strategy.UUID)
	private String id;
	private String name;
}
