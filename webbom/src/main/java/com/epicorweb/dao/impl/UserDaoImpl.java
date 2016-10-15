package com.epicorweb.dao.impl;

import java.util.List;
import com.epicorweb.dao.UserDao;
import org.hibernate.Query;
import com.epicorweb.entity.User;
import org.hibernate.persister.entity.Queryable;
import org.hibernate.query.internal.QueryImpl;


/**
 * @author zym
 * @date 2015-09-06
 */
public class UserDaoImpl extends AbstractBaseDaoImpl<User> implements UserDao {


    @SuppressWarnings("unchecked")
    public List<User> list() {
        List<User> tList = (List<User>) this.getHibernateTemplate().find("from User");
        return tList;
    }

    //@Override
    public User getUserByUserName(String username)
    {
//        List<User> tList =(List<User>)  getHibernateTemplate().findByNamedQueryAndNamedParam("from User where User.username=:username","username",username);
//        return tList.isEmpty() ? null : tList.get(0);
        Query query = (Query)getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createQuery("from User where username=:username");
        query.setParameter("username", username);
        return (User)(query.list().isEmpty() ? null : query.list().get(0));
    }

//本类继承了常用的公共方法，如需额外方法可自己在本类中进行定义！

}