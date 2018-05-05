package com.power.assistant.core.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.power.assistant.base.PageModel;
import com.power.assistant.base.PageParam;
import com.power.assistant.mapper.MenuMapper;
import com.power.assistant.mapper.RoleMapper;
import com.power.assistant.mapper.UserMapper;
import com.power.assistant.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserMapper userMapper;

    public PageModel<Role> list(PageParam param){
        PageHelper.offsetPage(param.getOffset(),param.getLimit());
        List<Role> roles = roleMapper.selectAll();
        PageInfo pageInfo = new PageInfo(roles);

        return PageModel.ok(pageInfo.getTotal(), pageInfo.getList());
    }

    public int saveOrUpdate(Role role) {
        if (role.getId() == null) {
            return roleMapper.insertRole(role);
        } else {
            return roleMapper.updateById(role);
        }
    }

    public int deleteRole(Long roleId) {

        List<User> users = roleMapper.selectRoleUser(roleId);
        if (users != null && users.size() > 0) {
            throw new RuntimeException("该角色下还包含有人员，不能删除!");
        }
        return roleMapper.deleteById(roleId);
    }

    public List<MenuVo> roleMenu(Long roleId) {

        List<Menu> menus = menuMapper.selectAll();
        List<Menu> roleMenus = menuMapper.selectRoleMenu(roleId);

        List<MenuVo> menuVos = new ArrayList<>();

        for (Menu menu : menus) {
            MenuVo vo = new MenuVo();
            BeanUtils.copyProperties(menu,vo);

            if (roleMenus != null && roleMenus.size() > 0) {
                for (Menu roleMenu : roleMenus) {
                    if (menu.getId().equals(roleMenu.getId())){
                        vo.setChecked(true);
                    }
                }
            }
            menuVos.add(vo);
        }

        return menuVos;
    }

    public int saveRoleMenu(Long roleId, String menuIds) {

        menuMapper.deleteRoleMenu(roleId);

        if (StringUtils.isEmpty(menuIds)){
            return 1;
        }

        String mIds[] = null;
        if (menuIds.contains(",")) {
            mIds = menuIds.split(",");
        } else {
            mIds = new String[]{menuIds};
        }

        List<RoleMenu> list = new ArrayList<>();
        for (String mId : mIds) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(Long.valueOf(mId));

            list.add(roleMenu);
        }

        return menuMapper.saveRoleMenu(list);
    }

    public List<UserVo> roleUser(Long roleId) {
        List<UserVo> users = userMapper.selectAllUser();
        List<UserVo> userVos = menuMapper.selectRoleUser(roleId);

        List<UserVo> roleUsers = new ArrayList<>();
        for (UserVo user : users) {
            UserVo vo = new UserVo();
            BeanUtils.copyProperties(user,vo);
            for (UserVo userVo : userVos) {
                if (user.getId().equals(userVo.getId())) {
                    vo.setChecked(true);
                }
            }
            roleUsers.add(vo);
        }
        return roleUsers;
    }

    public int saveRoleUser(Long roleId, String userIds) {
        menuMapper.deleteRoleUser(roleId);
        if (StringUtils.isEmpty(userIds)){
            return 1;
        }

        String mIds[] = null;
        if (userIds.contains(",")) {
            mIds = userIds.split(",");
        } else {
            mIds = new String[]{userIds};
        }

        List<RoleUser> list = new ArrayList<>();
        for (String mId : mIds) {
            RoleUser roleMenu = new RoleUser();
            roleMenu.setRoleId(roleId);
            roleMenu.setUserId(Long.valueOf(mId));

            list.add(roleMenu);
        }

        return menuMapper.saveRoleUser(list);
    }
}
