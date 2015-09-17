package com.yjh.dao;

import com.yjh.pojo.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YJH on 2015/9/17.
 */
@Repository
public class UserDao implements IUserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findByName(String loginName) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("username", loginName));

        return (User) criteria.uniqueResult();
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        return criteria.list();
    }
}
