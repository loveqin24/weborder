package com.epicorweb.dao;

import com.epicorweb.entity.Part;

import java.util.List;

public interface PartDao extends BaseDao<Part> {
    List<Part> list();
    List<Part> list(int offset, int length);
    Long getCount();
}
