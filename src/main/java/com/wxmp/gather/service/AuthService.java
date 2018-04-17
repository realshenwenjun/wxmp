package com.wxmp.gather.service;

import com.wxmp.gather.domain.User;

/**
 * Created by shenwenjun on 2018/4/13.
 */
public interface AuthService {
    public User getUserByWxOpenId(String wxOpenId);

    public User getUserByPhone(String userPhone);

    public void updateUser(User user);

    public void addUser(User user);

    public void updateUserByOpenId(User user);
}
