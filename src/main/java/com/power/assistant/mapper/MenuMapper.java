package com.power.assistant.mapper;


import com.power.assistant.model.Menu;
import com.power.assistant.model.RoleMenu;
import com.power.assistant.model.RoleUser;
import com.power.assistant.model.UserVo;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> selectRoleMenu(Long roleId);

    int deleteRoleMenu(Long roleId);

    int saveRoleMenu(List<RoleMenu> list);


    List<UserVo> selectRoleUser(Long roleId);

    int saveRoleUser(List<RoleUser> list);

    void deleteRoleUser(Long roleId);

    List<Menu> selectUserMenus(Long userId);
}