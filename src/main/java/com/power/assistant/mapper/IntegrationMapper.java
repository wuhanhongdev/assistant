package com.power.assistant.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.power.assistant.model.Integration;

public interface IntegrationMapper {

    int insert(Integration pojo);

    int insertList(List<Integration> pojos);

    int update(Integration pojo);

    int delete(Long id);
}
