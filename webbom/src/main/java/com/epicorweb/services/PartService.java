package com.epicorweb.services;

import com.epicorweb.entity.Part;

import java.util.List;

public interface PartService extends BaseService<Part> {
    Long getCount();
    List<Part> list();
    List<Part> list(int startRow, int pageSize);
}
