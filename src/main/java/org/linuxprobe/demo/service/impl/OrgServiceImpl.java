package org.linuxprobe.demo.service.impl;

import org.linuxprobe.crud.service.impl.BaseServiceImpl;
import org.linuxprobe.demo.model.Org;
import org.linuxprobe.demo.query.OrgQuery;
import org.linuxprobe.demo.service.OrgService;
import org.springframework.stereotype.Service;

@Service
public class OrgServiceImpl extends BaseServiceImpl<Org, OrgQuery> implements OrgService{

}
