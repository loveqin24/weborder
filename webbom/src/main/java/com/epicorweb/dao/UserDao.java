package com.epicorweb.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.epicorweb.entity.User;

/**
 * User对象Dao
 */
@Repository
public interface UserDao extends com.epicorweb.dao.BaseDao <com.epicorweb.entity.User> {

	List<User> list();

	User getUserByUserName(String username);

}
