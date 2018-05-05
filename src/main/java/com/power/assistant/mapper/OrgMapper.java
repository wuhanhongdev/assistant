package com.power.assistant.mapper;

import com.power.assistant.base.BaseMapper;
import com.power.assistant.model.Org;
import com.power.assistant.model.OrgVo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuhanhong
 * @date 2018 - 04 - 29
 */
public interface OrgMapper extends BaseMapper<Org> {

    List<OrgVo> selectOrgDetailInfo(Map<String, Object> params);


    int updateById(Org pojo);

    int deleteById(Long ordId);

    List<OrgVo> selectOrgInfo(Long orgId);
}
