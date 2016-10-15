/**
 * 
 */
package com.epicorweb.services.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

//import net.sf.json.JSONArray;

import com.epicorweb.dao.BaseDao;
import com.epicorweb.services.BaseService;

public abstract class AbstractBaseServiceImpl<T> implements BaseService<T> {

	public abstract BaseDao<T> getBaseDao();

	BaseDao<T> bd;

	public BaseDao<T> getBd() {
		return bd;
	}

	@Resource
	public void setBd(BaseDao<T> bd) {
		this.bd = bd;
	}

	public List<T> findByhql(String hql) {
		System.out.println(hql);
		List<T> tList = bd.findByhql(hql);
		return tList;
	}

	public List<T> findBysql(String sql) {
		List<T> aaList = bd.findBysql(sql);
		return aaList;
	}

	public List<T> getListForPage(final String sql, final int offset,
			final int length) {
		List<T> aaList = bd.getListForPage(sql, offset, length);
		return aaList;
	}

	public void save(Object obj) {
		bd.save(obj);
	}

	public void saveOrUpdate(Object obj) {
		bd.saveOrUpdate(obj);
	}

	public void update(Object obj) {
		bd.update(obj);
	}

	public void delete(Object entity, Serializable... ids) {
		bd.delete(entity, ids);
	}

	public void delete(Object obj) {
		bd.delete(obj);
	}
}
