package com.github.jcapitanmoreno.model.dao;

import com.github.jcapitanmoreno.model.connection.ConnectionMariaDB;
import com.github.jcapitanmoreno.model.entity.Admin;
import com.github.jcapitanmoreno.model.entity.Answer;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AdminDAO {
    private final static String LOGIN="SELECT * FROM admin WHERE user=? AND pasword=?";


    public boolean logIn(Admin entity) {
        boolean result = false;
        if (entity == null || entity.getUser() == null || entity.getPassword() == null){
            result = false;
        } else {
            //INSERT
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(LOGIN, Statement.RETURN_GENERATED_KEYS)) {
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
