/**
 * 
 */
package com.epicorweb.dao;

import java.io.Serializable;
import java.util.List;


public interface BaseDao<T> {
	public List<T> findByhql(String hql);

	public List<T> findBysql(String sql);

	public List<T> getListForPage(final String sql, final int offset,
								  final int length);

	public void save(Object obj);

	public void saveOrUpdate(Object obj);

	public void update(Object obj);

	public void delete(Object entity, Serializable... ids);

	public void delete(Object obj);

}
