package com.github.jcapitanmoreno.model.dao;

import com.github.jcapitanmoreno.model.entity.Player;
import com.github.jcapitanmoreno.model.connection.ConnectionMariaDB;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO implements DAO<Player, String> {

    private final static String INSERT="INSERT INTO player (playerID, name) VALUES (?,?)";
    private final static String UPDATE="UPDATE player SET name=?, earnedPoints=? WHERE playerID=?";
    private final static String FINDALL="SELECT * FROM player";
    private final static String FINDBYID="SELECT * FROM player WHERE playerID=?";
    private final static String DELETE="DELETE FROM player WHERE playerID=?";

    @Override
    public Player save(Player entity) {
        Player result = entity;
        if(entity==null || entity.getPlayerID()==null) return result;
        Player p = findById(entity.getPlayerID());  //si no está devuelve un autor por defecto
        if(p.getPlayerID()==null){
            //INSERT
            try(PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                pst.setString(1,entity.getPlayerID());
                pst.setString(2,entity.getName());
                pst.executeUpdate();
                //si fuera autoincremental yo tendría que leer getGeneratedKeys() -> setDNI
                        /*  ResultSet res = pst.getGeneratedKeys();
                            if(rs.next()){
                                entity.setDni(rs.getStrng(1));
                             }
                         */

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            //UPDATE
            try(PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)) {
                pst.setString(1,entity.getName());
                pst.setString(2,entity.getEarnedPoints());
                pst.setString(3,entity.getPlayerID());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Player delete(Player entity) throws SQLException {
        if(entity==null || entity.getPlayerID()==null) return entity;
        try(PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
            pst.setString(1,entity.getPlayerID());
            pst.executeUpdate();
        }
        return entity;
    }

    @Override
    public Player findById(String key) {
        Player result = new Player();
        if(key==null) return result;

        try(PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYID)) {
            pst.setString(1,key);
            ResultSet res = pst.executeQuery();
            if(res.next()){
                result.setPlayerID(res.getInt("playerID"));
                result.setName(res.getString("name"));
                result.setEarnedPoints(res.getInt("earnerPoints"));
                //Lazy
                //BookDAO bDAO = new BookDAO();
                //result.setBooks(bDAO.findByAuthor(result));
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List findAll() {
        List<Player> result = new ArrayList<>();

        try(PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDALL)) {

            ResultSet res = pst.executeQuery();
            while(res.next()){
                Player p = new Player();
                p.setPlayerID(res.getInt("dni"));
                p.setName(res.getString("name"));
                p.setEarnedPoints(res.getInt("earnerPoints"));
                //Lazy
                // a.setBooks(BookDAO.build().findByAuthor(a));
                result.add(p);
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws IOException {

    }
}
