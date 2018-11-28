package org.linuxprobe.demo.query;

import org.linuxprobe.crud.core.annoatation.Search;
import org.linuxprobe.crud.core.query.BaseQuery;
import org.linuxprobe.demo.model.OtaUpdate;

@Search(OtaUpdate.class)
public class OtaUpdateQuery extends BaseQuery{

}
