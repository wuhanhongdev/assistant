package com.power.assistant.mapper;


import com.power.assistant.base.BaseMapper;
import com.power.assistant.model.User;
import com.power.assistant.model.UserVo;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User> {

    List<UserVo> selectUserInfo(Map<String,Object> param);

    int insertUser(User user);

    int updateById(User user);

    int deleteById(Long userId);

    User userLogin(Map<String, Object> param);

    List<UserVo> selectAllUser();
}
