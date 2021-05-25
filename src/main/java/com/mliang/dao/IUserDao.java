package com.mliang.dao;

import com.mliang.model.User;

public interface IUserDao {
    User selectUser(long id);
    int saveUser(User user);
    User selectUserByPassword(User user);
    int selectUserByUserName(String userName);
}
