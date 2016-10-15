package com.epicorweb.dao.impl;

import com.epicorweb.dao.PartDao;
import com.epicorweb.entity.Part;
import java.util.List;
import org.hibernate.Query;


public class PartDaoImpl
  extends AbstractBaseDaoImpl<Part>
  implements PartDao
{
  public List<Part> list()
  {
    String queryString = "from Part ";
    List<Part> list = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(queryString).list();
    return list;
  }
  
  public List<Part> list(int offset, int length)
  {
    String queryString = "from Part ";
    
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(queryString);
    query.setFirstResult(offset);
    query.setMaxResults(length);
    List<Part> list = query.list();
    return list;
  }
  
  public Long getCount()
  {
    String queryString = "select count(*) from Part ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(queryString);
    Long count = (Long)query.uniqueResult();
    return count;
  }
}
