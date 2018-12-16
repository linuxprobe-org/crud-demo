package org.linuxprobe.demo.service.impl;

import org.linuxprobe.crud.service.impl.UniversalServiceImpl;
import org.linuxprobe.demo.model.Org;
import org.linuxprobe.demo.query.OrgQuery;
import org.linuxprobe.demo.service.OrgService;
import org.springframework.stereotype.Service;

@Service
public class OrgServiceImpl extends UniversalServiceImpl<Org, String, OrgQuery> implements OrgService {

}
