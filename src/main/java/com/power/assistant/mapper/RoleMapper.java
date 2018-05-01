package com.power.assistant.mapper;

import com.power.assistant.base.BaseMapper;
import com.power.assistant.model.Role;

/**
 * @author wuhanhong
 * @date 2018 - 04 - 29
 */
public interface RoleMapper extends BaseMapper<Role> {

    int insertRole(Role role);
    int updateById(Role role);
    int deleteById(Long roleId);
}
