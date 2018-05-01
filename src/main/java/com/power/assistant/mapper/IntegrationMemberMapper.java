package com.power.assistant.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.power.assistant.model.IntegrationMember;

public interface IntegrationMemberMapper {

    int insert(IntegrationMember pojo);

    int insertList(List< IntegrationMember> pojos);

    int update(IntegrationMember pojo);

    int delete(Long id);

    List<IntegrationMember> selectIntegrationMember(Long id);

    List<IntegrationMember> selectIntegrationMembers(String memberId);
}
