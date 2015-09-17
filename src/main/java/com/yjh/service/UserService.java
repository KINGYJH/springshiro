package com.yjh.service;

import com.yjh.dao.IUserDao;
import com.yjh.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YJH on 2015/9/17.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User findByName(String loginName) {
        return userDao.findByName(loginName);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
