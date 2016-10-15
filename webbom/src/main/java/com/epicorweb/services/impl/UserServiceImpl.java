package com.epicorweb.services.impl;

import java.util.List;

import com.epicorweb.dao.BaseDao;
import com.epicorweb.entity.User;

import javax.annotation.Resource;


public class UserServiceImpl extends com.epicorweb.services.impl.AbstractBaseServiceImpl<com.epicorweb.entity.User> implements com.epicorweb.services.UserService{

    private com.epicorweb.dao.UserDao userDao;

//	public com.epicor.dao.UserDao getUserDao() {
////		super.setBd(userDao);
//		return userDao;
//	}
//	
	
	public void setUserDao(com.epicorweb.dao.UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public BaseDao<User> getBaseDao() {
		// TODO Auto-generated method stub
		return userDao;
	}

	public List<User> list() {
		List<User> tList = userDao.list();
		return tList;
	}


	  public User getUserByUserName(String username)
	  {
	    User user = this.userDao.getUserByUserName(username);
	    return user;
	  }
}
