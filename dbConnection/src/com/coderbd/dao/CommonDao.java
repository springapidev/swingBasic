package com.coderbd.dao;

import com.coderbd.domain.Employees;
import java.util.List;

public interface CommonDao {

    List<?> getList();

    void insert(Employees e);

}
