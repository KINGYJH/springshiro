package com.yjh.service;

import com.yjh.pojo.User;

import java.util.List;

/**
 * Created by YJH on 2015/9/17.
 */
public interface IUserService {

    User findByName(String loginName);

    List<User> findAll();

}
