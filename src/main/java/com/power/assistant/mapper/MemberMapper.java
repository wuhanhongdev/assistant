package com.power.assistant.mapper;

import com.power.assistant.model.MemberVo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.power.assistant.model.Member;

public interface MemberMapper {

    int insert(Member pojo);

    int insertList(List<Member> pojos);

    int update(Member pojo);

    int delete(Long id);

    List<MemberVo> selectMemberInfo(Map<String, Object> objectObjectHashMap);

    int insertContent(String content);

    int updateContent(String content);

    String selectContent();
}
