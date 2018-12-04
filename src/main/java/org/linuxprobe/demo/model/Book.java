package org.linuxprobe.demo.model;

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
	private Long id;
	private String name;
}
