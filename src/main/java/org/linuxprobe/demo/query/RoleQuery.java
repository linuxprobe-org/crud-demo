package org.linuxprobe.demo.query;

import org.linuxprobe.crud.core.annoatation.Query;
import org.linuxprobe.crud.core.query.BaseQuery;
import org.linuxprobe.crud.core.query.param.impl.StringParam;
import org.linuxprobe.demo.model.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Query(Role.class)
public class RoleQuery extends BaseQuery {
	private StringParam name;
}
