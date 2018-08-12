package org.linuxprobe.demo.query;

import org.linuxprobe.crud.core.annoatation.Search;
import org.linuxprobe.crud.core.query.BaseQuery;
import org.linuxprobe.crud.core.query.param.impl.StringParam;
import org.linuxprobe.demo.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Search(Role.class)
public class RoleQuery extends BaseQuery{
	private StringParam name;
}
