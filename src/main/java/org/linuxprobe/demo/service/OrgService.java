package org.linuxprobe.demo.service;

import org.linuxprobe.crud.service.UniversalService;
import org.linuxprobe.demo.model.Org;
import org.linuxprobe.demo.query.OrgQuery;

public interface OrgService extends UniversalService<Org, String, OrgQuery> {

}
