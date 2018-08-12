package org.linuxprobe.demo.query;

import org.linuxprobe.crud.core.annoatation.Search;
import org.linuxprobe.crud.core.query.BaseQuery;
import org.linuxprobe.crud.core.query.param.impl.StringParam;
import org.linuxprobe.demo.model.Org;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Search(Org.class)
public class OrgQuery extends BaseQuery{
	private StringParam name;
}
