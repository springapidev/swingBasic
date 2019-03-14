package com.coderbd.dao;

import com.coderbd.pojo.Role;
import com.coderbd.pojo.User;

public interface LoginDao {

    User findByUsernameAndPassword(String username, String password);
}
