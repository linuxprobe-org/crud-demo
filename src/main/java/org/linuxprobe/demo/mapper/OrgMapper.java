package org.linuxprobe.demo.mapper;

import java.util.List;

import org.linuxprobe.demo.model.Org;
import org.linuxprobe.demo.query.OrgQuery;

public interface OrgMapper {
	List<Org> select(OrgQuery param);

	/** 根据主键查询 */
	Org selectByPrimaryKey(String id);
}
