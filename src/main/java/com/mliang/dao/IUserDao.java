package com.mliang.dao;

import com.mliang.model.User;

public interface IUserDao {
    User selectUser(long id);
}
