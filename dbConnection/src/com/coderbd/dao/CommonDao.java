package com.coderbd.dao;

import java.util.List;

public interface CommonDao {

    List<?> getList();

    void insert();

    void update();

    void delete();
}
