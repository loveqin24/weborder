/**
 * 
 */
package com.epicorweb.dao.impl;

import java.util.List;
import java.io.Serializable;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.epicorweb.dao.BaseDao;


public class AbstractBaseDaoImpl<T> extends HibernateDaoSupport implements
		BaseDao<T> {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	
	
	/**
	 * 根据传值进来的hql语句进行查找
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByhql(String hql) {
		System.out.println("======" + hql);
		List<T> tList = (List<T>) this.getHibernateTemplate().find(hql);

		return tList;
	}

	/**
	 * 执行原生sql进行查询
	 */

	@SuppressWarnings("unchecked")
	public List<T> findBysql(final String sql) {
		List<T> list = (List<T>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery query = session.createSQLQuery(sql);
						List<T> list = query.list();
						return list;
					}
				});
		return list;
	}

	/**
	 * 执行原生sql进行分页查询
	 */
	@SuppressWarnings("unchecked")
	public List<T> getListForPage(final String sql, final int offset,
			final int length) {
		List<T> list = (List<T>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session)
							throws HibernateException {

						SQLQuery query = session.createSQLQuery(sql);
						query.setFirstResult(offset);// 设置取�?的开始位�?
						query.setMaxResults(length); // 设置读取数据的记录条�?
						List<T> list = query.list();
						return list;
					}

				});
		return list;
	}

	/**
	 * 利用save()方法保存对象的详细信息
	 */

	public void save(Object obj) {
		this.getHibernateTemplate().save(obj);
	}

	public void saveOrUpdate(Object obj) {
		this.getHibernateTemplate().saveOrUpdate(obj);
	}

	/**
	 * 利用update()方法修改对象的详细信息
	 */

	public void update(Object obj) {
		this.getHibernateTemplate().update(obj);
	}

	/**
	 * 多个id进行删除
	 */
	@SuppressWarnings("unchecked")
	public void delete(Object entity, Serializable... ids) {
		for (Serializable id : ids) {
			T t = (T) this.getHibernateTemplate().load(entity.getClass(), id);
			this.getHibernateTemplate().delete(t);
		}
	}

	/**
	 * 根据某一个对象删除进行删除
	 */
	public void delete(Object obj) {
		this.getHibernateTemplate().delete(obj);
	}

}
