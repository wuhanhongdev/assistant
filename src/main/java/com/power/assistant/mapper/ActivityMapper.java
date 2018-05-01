package com.power.assistant.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.power.assistant.model.Activity;

public interface ActivityMapper {

    int insert( Activity pojo);

    int insertList(List<Activity> pojos);

    int update(Activity pojo);

    int delete(Long id);

    List<Activity> selectActivityInfo(Map<Object, Object> param);
}
