package com.power.assistant.core.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.power.assistant.base.DataModel;
import com.power.assistant.base.PageModel;
import com.power.assistant.base.PageParam;
import com.power.assistant.mapper.ActivityMapper;
import com.power.assistant.mapper.IntegrationMapper;
import com.power.assistant.mapper.IntegrationMemberMapper;
import com.power.assistant.mapper.MemberMapper;
import com.power.assistant.model.Activity;
import com.power.assistant.model.IntegrationMember;
import com.power.assistant.model.Member;
import com.power.assistant.model.MemberVo;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.xmlbeans.impl.jam.mutable.MAnnotatedElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuhanhong
 * @date 2018 - 04 - 30
 */

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private IntegrationMemberMapper integrationMemberMapper;
    @Autowired
    private CoderService coderService;
    @Value("${config.ueditor.web-qr-code-path}")
    private String qrCodePath;

    public PageModel partyList(PageParam param,Long orgId,String name) {
        PageHelper.offsetPage(param.getOffset(),param.getLimit());
        Map map =  new HashMap<>();
        if (orgId != null) {
            map.put("orgId",orgId);
        }
        if (!StringUtils.isEmpty(name)) {
            map.put("name",name);
        }
        List<MemberVo> memberVos = memberMapper.selectMemberInfo(map);
        PageInfo<MemberVo> pageInfo = new PageInfo<>(memberVos);

        return PageModel.ok(pageInfo.getTotal(),pageInfo.getList());
    }

    public int deleteMember(Long memberId) {
        return memberMapper.delete(memberId);
    }

    public int saveOrUpdate(Member member) {
        if (member.getId() == null) {
            member.setPass(0);
            int save = memberMapper.insert(member);
            if(save == 1) {
                //生成二维码
                String realPath = qrCodePath + member.getId();
                String path = coderService.encode(realPath);

                Member updateMember = new Member();
                updateMember.setId(member.getId());
                updateMember.setQrcode(path);

                return memberMapper.update(updateMember);
            } else {
                throw new RuntimeException("保存失败");
            }
        } else {
            return memberMapper.update(member);
        }
    }

    public PageModel activityList(PageParam param,Long orgId) {

        Map map = new HashMap<>();

        if (orgId != null) {
            map.put("orgId",orgId);
        }
        PageHelper.offsetPage(param.getOffset(),param.getLimit());
        List<Activity> activities = activityMapper.selectActivityInfo(map);
        PageInfo<Activity> pageInfo = new PageInfo<>(activities);

        return PageModel.ok(pageInfo.getTotal(),pageInfo.getList());
    }

    public List<Activity> activityQuery(Long id){
        HashMap map = new HashMap();
        map.put("orgId",id);

        return activityMapper.selectActivityInfo(map);
    }


    public int saveOrUpdateActivity(Activity activity) {
        if (activity.getId() == null) {
            HashMap param = new HashMap<>();
            param.put("orgId",activity.getOrgId());
            param.put("yearNo",activity.getYearNo());
            param.put("period",activity.getPeriod());
            List<Activity> activities = activityMapper.selectActivityInfo(param);
            if (activities != null && activities.size() > 0) {
                throw new RuntimeException("该机构下已存在同年度月度主题活动");
            }
            return activityMapper.insert(activity);
        }else {
            return activityMapper.update(activity);
        }

    }

    public int deleteActivity(Long activityId) {
        return activityMapper.delete(activityId);
    }

    /**
     *
     * @param memberId
     * @return
     */
    public MemberVo memberDetail(Long memberId) {
        HashMap param = new HashMap();
        param.put("memberId",memberId);
        List<MemberVo> vos = memberMapper.selectMemberInfo(param);

        if (vos != null && vos.size() > 0) {
            return vos.get(0);
        }else {
            return new MemberVo();
        }
    }

    public PageModel integrationList(PageParam param,Long memberId) {
        PageHelper.offsetPage(param.getOffset(),param.getLimit());
        List<IntegrationMember> integrationMembers = integrationMemberMapper.selectIntegrationMember(memberId);
        PageInfo<IntegrationMember> pageInfo = new PageInfo<>(integrationMembers);

        return PageModel.ok(pageInfo.getTotal(),pageInfo.getList());
    }

    public int saveOrUpdateIntegration(IntegrationMember integrationMember) {
        if (integrationMember.getId() == null) {
            return integrationMemberMapper.insert(integrationMember);
        } else {
            return integrationMemberMapper.update(integrationMember);
        }
    }

    public int deleteIntegrationMember(Long integrationId) {
        return integrationMemberMapper.delete(integrationId);
    }

    public List<IntegrationMember> selectIntegrationMember(String memberIds) {
        return integrationMemberMapper.selectIntegrationMembers(memberIds);
    }

    public int saveOrUpdateContent(String content,Long id) {
        if (id == null) {
            return memberMapper.insertContent(content);
        } else {
            return memberMapper.updateContent(content);
        }
    }

    public DataModel contentQuery() {
        return DataModel.ok(memberMapper.selectContent());
    }
}
