package com.mliang.service;

import com.mliang.model.User;

public interface IUserService {

    public User selectUser(long userId);
    public int saveUser(User user);
    public User selectUserByPassword(User user);
    public User selectUserByUserName(String userName);
}
