package com.power.assistant.core.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

    public PageModel partyList(PageParam param) {
        PageHelper.offsetPage(param.getOffset(),param.getLimit());
        List<MemberVo> memberVos = memberMapper.selectMemberInfo(new HashMap<>());
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

    public PageModel activityList(PageParam param) {
        PageHelper.offsetPage(param.getOffset(),param.getLimit());
        List<Activity> activities = activityMapper.selectActivityInfo(new HashMap<>());
        PageInfo<Activity> pageInfo = new PageInfo<>(activities);

        return PageModel.ok(pageInfo.getTotal(),pageInfo.getList());
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
}
