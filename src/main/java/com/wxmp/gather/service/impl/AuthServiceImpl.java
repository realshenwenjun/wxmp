package com.wxmp.gather.service.impl;

import com.wxmp.core.util.StringUtil;
import com.wxmp.gather.domain.User;
import com.wxmp.gather.mapper.UserMapper;
import com.wxmp.gather.service.AuthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by shenwenjun on 2018/4/13.
 */
@Service("authService")
public class AuthServiceImpl implements AuthService{

    @Resource
    UserMapper userMapper;

    @Override
    public User getUserByWxOpenId(String wxOpenId) {
        if (StringUtil.isEmpty(wxOpenId))
            return null;
        User u = userMapper.selectByWxOpenId(wxOpenId);
        if (u == null){ // 没有就创建
            u = new User();
            u.setCreateTime(new Date());
            u.setId(StringUtil.getRandomLengthString(32));
            u.setWxOpenid(wxOpenId);
            userMapper.insertSelective(u);
        }
        return u;
    }

    @Override
    public User getUserByPhone(String userPhone) {
        if (StringUtil.isEmpty(userPhone))
            return null;
        return userMapper.selectByUserPhone(userPhone);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void addUser(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public void updateUserByOpenId(User user) {
        userMapper.updateByWxOpenIdSelective(user);
    }
}
