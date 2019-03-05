package com.coderbd.dao;

import com.coderbd.pojo.Role;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RoleDao {

    void createTable(String sql);

    void save(Role role);

    void update(Role role);

    Role getRoleById(int id);

    Role getRoleByRoleName(String roleName);

    void delete(int id);

    List<Role> getRoles();

}
