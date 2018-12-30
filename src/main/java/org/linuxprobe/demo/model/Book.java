package org.linuxprobe.demo.model;

import javax.validation.constraints.Size;

import org.linuxprobe.crud.core.annoatation.Column;
import org.linuxprobe.crud.core.annoatation.Entity;
import org.linuxprobe.crud.core.annoatation.PrimaryKey;
import org.linuxprobe.crud.core.annoatation.PrimaryKey.Strategy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
public class Book {
	@PrimaryKey(Strategy.NATIVE)
	@Column("id")
	private Long ids;
	@Size(min = 100)
	private String name;
}
