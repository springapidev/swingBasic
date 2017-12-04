package com.coderbd.mobile.service;


import java.sql.Connection;
import java.util.List;
import com.coderbd.mobile.domain.User;
import com.coderbd.mobile.conn.MySqlDbConnection;
import com.coderbd.mobile.dao.UserDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajaul Islam
 */
public class UserService implements UserDao {

    Connection conn = MySqlDbConnection.getConnection();

    @Override
    public void save(User s) {
        try {
            if (s.getUsername() != null) {

                PreparedStatement stmt = conn.prepareStatement("insert into user(name,email,mobile, username,password,user_type) values(?,?,?,?,?,?)");

                stmt.setString(1, s.getName());
                stmt.setString(2, s.getEmail());
                stmt.setString(3, s.getMobile());
                stmt.setString(4, s.getUsername());
                stmt.setString(5, s.getPassword());
                stmt.setString(6, s.getUserType());

                int i = stmt.executeUpdate();
                System.out.println(i + " record inserted");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(User s) {
        try {
            if (s.getId() != 0) {
                PreparedStatement stmt = conn.prepareStatement("update user SET name=?,email=?,mobile=?,username=?, password=?, user_type=?  where id=?");

                stmt.setString(1, s.getName());
                stmt.setString(2, s.getEmail());
                stmt.setString(3, s.getMobile());
                stmt.setString(4, s.getUsername());
                stmt.setString(5, s.getPassword());
                stmt.setString(6, s.getUserType());
                stmt.setInt(7, s.getId());
                int i = stmt.executeUpdate();

                System.out.println(i + " record Updated");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            if (id != 0) {
                PreparedStatement stmt = conn.prepareStatement("delete from user where id=?");

                stmt.setInt(1, id);

                int i = stmt.executeUpdate();
                System.out.println(i + " record Deleted");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<User> getList() {
        List<User> list = new ArrayList<>();
        try {
            User p;
            PreparedStatement stmt = conn.prepareStatement("select * from user");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p = new User();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setEmail(rs.getString(3));
                p.setMobile(rs.getString(4));
                p.setUsername(rs.getString(5));
                p.setPassword(rs.getString(6));
                p.setUserType(rs.getString(7));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public User getUser(int id) {
        User p = new User();
        try {

            PreparedStatement stmt = conn.prepareStatement("select * from user where id=?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setEmail(rs.getString(3));
                p.setMobile(rs.getString(4));
                p.setUsername(rs.getString(5));
                p.setPassword(rs.getString(6));
                p.setUserType(rs.getString(7));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public User getUserByUsername(String username) {
        User p = new User();
        try {

            PreparedStatement stmt = conn.prepareStatement("select * from user where username=?");
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setEmail(rs.getString(3));
                p.setMobile(rs.getString(4));
                p.setUsername(rs.getString(5));
                p.setPassword(rs.getString(6));
                p.setUserType(rs.getString(7));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

}
