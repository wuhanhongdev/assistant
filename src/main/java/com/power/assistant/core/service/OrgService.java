package com.power.assistant.core.service;

import com.power.assistant.mapper.OrgMapper;
import com.power.assistant.model.Org;
import com.power.assistant.model.OrgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class OrgService {

    @Autowired
    private OrgMapper orgMapper;

    public List<OrgVo> selectOrgs() {
        return orgMapper.selectOrgDetailInfo(new HashMap<>());
    }

    public int saveOrUpdate(Org org) {
        if (org.getId() == null) {
            return orgMapper.insertSelective(org);
        } else {
            return orgMapper.updateById(org);
        }
    }

    public OrgVo orgInfo(Long orgId) {
        HashMap param = new HashMap<>(1);
        param.put("orgId",orgId);

        List<OrgVo> vos = orgMapper.selectOrgDetailInfo(param);
        return vos != null && vos.size() > 0 ? vos.get(0) : null;
    }

    public int delete(Long orgId) {
        return orgMapper.deleteById(orgId);
    }
}
