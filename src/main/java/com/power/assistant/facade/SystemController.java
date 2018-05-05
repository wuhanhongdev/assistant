package com.power.assistant.facade;

import com.power.assistant.base.*;
import com.power.assistant.core.annotation.Authentication;
import com.power.assistant.core.service.MenuService;
import com.power.assistant.core.service.OrgService;
import com.power.assistant.core.service.RoleService;
import com.power.assistant.core.service.UserService;
import com.power.assistant.mapper.MenuMapper;
import com.power.assistant.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wuhanhong
 * @date 2018 - 04 - 29
 */
@RestController
@RequestMapping("/system")
public class SystemController {

    @Value("${config.ueditor.serverPath.profile}")
    private String serverPath;
    @Value("${web.profile}")
    private String profilePath;
    @Autowired
    private OrgService orgService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @Authentication
    @PostMapping("/menu/list")
    public PageModel<Menu> list(PageParam param) {
        try {
            return menuService.list(param);
        } catch (Exception e) {
            return PageModel.error();
        }
    }

    @Authentication
    @GetMapping("/org/list")
    public DataModel orgList(HttpServletRequest request){
        try {
            User user = (User) request.getSession().getAttribute(Constants.SESSION);
            return DataModel.ok(orgService.selectOrgs(user.getOrgId()));
        } catch (Exception e) {
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @GetMapping("/org/list2")
    public DataModel orgList2(){
        try {
            return DataModel.ok(orgService.selectOrgs(Long.valueOf(1)));
        } catch (Exception e) {
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @GetMapping("/org/delete")
    public DataModel delete(@RequestParam("orgId") Long orgId){
        try {
            return DataModel.ok(orgService.delete(orgId));
        } catch (Exception e) {
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @GetMapping("/org/query")
    public DataModel org(Long orgId){
        try {
            return DataModel.ok(orgService.orgInfo(orgId));
        } catch (Exception e) {
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @PostMapping("/org/saveOrUpdate")
    public DataModel saveOrUpdate(Org org) {
        try {
            return DataModel.ok(orgService.saveOrUpdate(org));
        } catch (Exception e) {
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @PostMapping("/user/saveOrUpdate")
    public DataModel saveOrUpdateUser(User user, MultipartFile photo) {
        try {
            if (photo != null) {
                String[] upload = FileUtils.upload(photo,serverPath,profilePath);
                user.setPhotosrc(upload[0]);
            }
            return DataModel.ok(userService.saveOrUpdate(user));
        } catch (Exception e) {
            e.printStackTrace();
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @GetMapping("/user/delete")
    public DataModel deleteUser(Long userId){
        try {
            return DataModel.ok(userService.deleteUser(userId));
        } catch (Exception e) {
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @PostMapping("/user/list")
    public PageModel<UserVo> userList(PageParam param, Long orgId, String name){

        try {
            return userService.list(param,orgId,name);
        } catch (Exception e) {
            return PageModel.error();
        }
    }

    @Authentication
    @PostMapping("/role/list")
    public PageModel<Role> roleList(PageParam param){
        try {
            return roleService.list(param);
        } catch (Exception e) {
            return PageModel.error();
        }
    }

    @Authentication
    @PostMapping("/role/saveOrUpdate")
    public DataModel saveOrUpdateRole(Role role) {
        try {
            return DataModel.ok(roleService.saveOrUpdate(role));
        } catch (Exception e) {
            e.printStackTrace();
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @GetMapping("/role/delete")
    public DataModel deleteRole(Long roleId){
        try {
            return DataModel.ok(roleService.deleteRole(roleId));
        } catch (Exception e) {
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @GetMapping("/role/roleMenu")
    public DataModel roleMenu(Long roleId){
        try {
            return DataModel.ok(roleService.roleMenu(roleId));
        } catch (Exception e) {
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @GetMapping("/role/roleUser")
    public DataModel roleUser(Long roleId){
        try {
            return DataModel.ok(roleService.roleUser(roleId));
        } catch (Exception e) {
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @PostMapping("/role/saveRoleMenu")
    public DataModel saveRoleMenu(Long roleId,String menuIds){
        try {
            return DataModel.ok(roleService.saveRoleMenu(roleId,menuIds));
        } catch (Exception e) {
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @PostMapping("/role/saveRoleUser")
    public DataModel saveRoleUser(Long roleId,String userIds){
        try {
            return DataModel.ok(roleService.saveRoleUser(roleId,userIds));
        } catch (Exception e) {
            return DataModel.error(e.getMessage());
        }
    }
}
