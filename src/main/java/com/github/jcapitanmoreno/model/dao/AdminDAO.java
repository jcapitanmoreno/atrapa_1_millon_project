package com.github.jcapitanmoreno.model.dao;

import com.github.jcapitanmoreno.model.connection.ConnectionXamp;
import com.github.jcapitanmoreno.model.entity.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDAO {
    private final static String LOGIN="SELECT * FROM admin WHERE user=? AND pasword=?";

    /**
     * Attempts to log in an admin user based on the provided credentials.
     *
     * @param entity The Admin entity containing the username and password.
     * @return true if the login is successful, false otherwise.
     */
    public boolean logIn(Admin entity) {
        boolean result = false;
        if (entity == null || entity.getUser() == null || entity.getPassword() == null){
            result = false;
        } else {
            //INSERT
            try (PreparedStatement pst = ConnectionXamp.getConnection().prepareStatement(LOGIN, Statement.RETURN_GENERATED_KEYS)) {
                pst.setString(1, entity.getUser());
                pst.setString(2, entity.getPassword());
                ResultSet valid = pst.executeQuery();
                if (valid.next()){
                    result=true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
