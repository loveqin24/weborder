package com.epicorweb.services.impl;

import com.epicorweb.dao.BaseDao;
import com.epicorweb.dao.PartDao;
import com.epicorweb.entity.Part;
import com.epicorweb.services.PartService;
import java.util.List;

public class PartServiceImpl
  extends AbstractBaseServiceImpl<Part>
  implements PartService
{
  private PartDao partDao;

  public void setPartDao(PartDao partDao)
  {
    this.partDao = partDao;
  }

  @Override
  public BaseDao<Part> getBaseDao()
  {
    return this.partDao;
  }
  
  public List<Part> list()
  {
    List<Part> list = this.partDao.list();
    return list;
  }
  
  public Long getCount()
  {
    return this.partDao.getCount();
  }
  
  public List<Part> list(int startRow, int pageSize)
  {
    List<Part> list = this.partDao.list(startRow, pageSize);
    return list;
  }
}
