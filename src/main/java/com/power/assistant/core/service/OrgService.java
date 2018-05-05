package com.power.assistant.core.service;

import com.power.assistant.mapper.MemberMapper;
import com.power.assistant.mapper.OrgMapper;
import com.power.assistant.mapper.UserMapper;
import com.power.assistant.model.MemberVo;
import com.power.assistant.model.Org;
import com.power.assistant.model.OrgVo;
import com.power.assistant.model.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrgService {

    @Autowired
    private OrgMapper orgMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private UserMapper userMapper;

    public List<OrgVo> selectOrgs(Long orgId) {
        return orgMapper.selectOrgInfo(orgId);
    }

    public int saveOrUpdate(Org org) {
        if (org.getId() == null) {
            org.setStatus(1L);
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

        //查询是否有党员或者操作员
        HashMap<String, Object> queryMember = new HashMap<>();
        queryMember.put("orgId",orgId);
        List<MemberVo> memberVos = memberMapper.selectMemberInfo(queryMember);
        if(memberVos != null && memberVos.size() > 0) {
            throw new RuntimeException("该机构下有党员,不能被删除");
        }
        List<UserVo> userVos = userMapper.selectUserInfo(queryMember);
        if(userVos != null && userVos.size() > 0) {
            throw new RuntimeException("该机构下有操作员,不能被删除");
        }

        return orgMapper.deleteById(orgId);
    }
}
