package org.linuxprobe.demo.query;

import org.linuxprobe.crud.core.annoatation.Query;
import org.linuxprobe.crud.core.query.BaseQuery;
import org.linuxprobe.demo.model.OtaUpdate;

@Query(OtaUpdate.class)
public class OtaUpdateQuery extends BaseQuery {

}
