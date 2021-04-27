package com.mliang.service.impl;

import com.mliang.dao.IUserDao;
import com.mliang.model.User;
import com.mliang.service.IUserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    public User selectUser(long userId) {
        System.out.println("我是service层");
        return userDao.selectUser(userId);
    }

    @Override
    public int saveUser(User user) {
        int result = userDao.saveUser(user);
        return result;
    }

    @Override
    public User selectUserByPassword(User user) {
        return userDao.selectUserByPassword(user);
    }
}