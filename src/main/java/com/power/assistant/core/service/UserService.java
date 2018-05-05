package com.power.assistant.core.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.power.assistant.base.MD5;
import com.power.assistant.base.PageModel;
import com.power.assistant.base.PageParam;
import com.power.assistant.mapper.UserMapper;
import com.power.assistant.model.User;
import com.power.assistant.model.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;

    public PageModel<UserVo> list(PageParam param, Long orgId, String name) {

        PageHelper.offsetPage(param.getOffset(),param.getLimit());
        Map map =  new HashMap<>();
        if (orgId != null) {
            map.put("orgId",orgId);
        }
        if (!StringUtils.isEmpty(name)) {
            map.put("name",name);
        }
        List<UserVo> users = userMapper.selectUserInfo(map);
        PageInfo pageInfo = new PageInfo(users);

        return PageModel.ok(pageInfo.getTotal(), pageInfo.getList());
    }

    public int saveOrUpdate(User user) {
        if (!StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(MD5.crypt(user.getPassword()));
        }else {
            user.setPassword(null);
        }
        if (user.getId() == null) {

            //判断登录
            Map map =  new HashMap<>();
            map.put("loginname",user.getLoginname());
            List<UserVo> users = userMapper.selectUserInfo(map);
            if (users != null && users.size()>0){
                throw new RuntimeException("登录名重复");
            }

            return userMapper.insertUser(user);
        } else {
            return userMapper.updateById(user);
        }
    }

    public int deleteUser(Long userId) {
        return userMapper.deleteById(userId);
    }

    public User authentication(String username, String pwd) {
        Map<String,Object> param = new HashMap<>();
        param.put("username",username);
        param.put("pwd",MD5.crypt(pwd));
        User user = userMapper.userLogin(param);

        if (user == null) {
            throw new RuntimeException("用户名或者密码错误");
        }

        return user;
    }

    public int updatePassword(Long id, String crypt) {

        User user = new User();
        user.setId(id);
        user.setPassword(crypt);

        return userMapper.updateById(user);
    }
}
