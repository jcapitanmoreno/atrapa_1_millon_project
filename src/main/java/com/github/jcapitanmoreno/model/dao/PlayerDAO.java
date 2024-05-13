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

public class PlayerDAO implements DAO<Player, Integer> {

    private final static String INSERT="INSERT INTO player (name) VALUES (?)";
    private final static String UPDATE="UPDATE player SET name=?, earnedPoints=? WHERE playerID=?";
    private final static String FINDALL="SELECT * FROM player";
    private final static String FINDBYID="SELECT * FROM player WHERE playerID=?";
    private final static String DELETE="DELETE FROM player WHERE playerID=?";

    @Override
    public Player save(Player entity) {
        Player result = entity;
        System.out.println(entity.getPlayerID());
        if(entity.getName() == null){
            result = null;
        }else {
            System.out.println("soy concha entro");
            Player p = findById(entity.getPlayerID());//si no está devuelve un player por defecto (yo no lo tengo por defecto)
            if(p == null){
                System.out.println("que entro vale?");
                //INSERT
                try(PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                    pst.setString(1,entity.getName());
                    pst.executeUpdate();
                    //si fuera autoincremental yo tendría que leer getGeneratedKeys() -> setDNI
                    ResultSet res = pst.getGeneratedKeys();
                    if (res.next()) {
                        int generatedID = res.getInt(1);
                        entity.setPlayerID(generatedID);
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                //UPDATE
                try(PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)) {
                    pst.setString(1,entity.getName());
                    pst.setInt(2,entity.getEarnedPoints());
                    pst.setInt(3,entity.getPlayerID());
                    pst.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public Player delete(Player entity) throws SQLException {
        if(entity==null || entity.getPlayerID()==-1){
            entity = null;
        }else {
            try(PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
                pst.setInt(1,entity.getPlayerID());
                pst.executeUpdate();
            }
        }
        return entity;
    }

    @Override
    public Player findById(Integer key) {
        Player result = new Player();
        if(key==-1) {
            result = null;
        } else {
            try(PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYID)) {
                pst.setInt(1,key);
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
                p.setPlayerID(res.getInt("playerID"));
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
