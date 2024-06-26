package com.github.jcapitanmoreno.model.dao;

import com.github.jcapitanmoreno.model.entity.Player;
import com.github.jcapitanmoreno.model.connection.ConnectionXamp;
import com.github.jcapitanmoreno.utils.LogRead;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO implements DAO<Player, Integer> {

    private final static String INSERT="INSERT INTO player (name,earnedPoints) VALUES (?,?)";
    private final static String UPDATE="UPDATE player SET name=?, earnedPoints=? WHERE playerID=?";
    private final static String FINDALL="SELECT * FROM player";
    private final static String FINDBYID="SELECT * FROM player WHERE playerID=?";
    private final static String DELETE="DELETE FROM player WHERE playerID=?";

    /**
     * Saves a Player entity to the database. If the player does not already exist, it will be inserted.
     * If the player exists, its information will be updated.
     *
     * @param entity The Player entity to be saved.
     * @return The saved Player entity, or null if the name is not provided.
     */
    @Override
    public Player save(Player entity) {
        Player result = entity;

        if(entity.getName() == null){
            result = null;
        }else {
            Player p = findById(entity.getPlayerID());//si no está devuelve un player por defecto (yo no lo tengo por defecto)
            if(p == null){
                //INSERT
                try(PreparedStatement pst = ConnectionXamp.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                    pst.setString(1,entity.getName());
                    pst.setInt(2,entity.getEarnedPoints());
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
                try(PreparedStatement pst = ConnectionXamp.getConnection().prepareStatement(UPDATE)) {
                    pst.setString(1,entity.getName());
                    pst.setInt(2,entity.getEarnedPoints());
                    pst.setInt(3,entity.getPlayerID());
                    pst.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    LogRead.logWritter(result);
                }
            }
        }
        return result;
    }

    /**
     * Deletes a Player entity from the database.
     *
     * @param entity The Player entity to be deleted.
     * @return The deleted Player entity, or null if the entity is null or has an invalid ID.
     * @throws SQLException If a database access error occurs.
     */
    @Override
    public Player delete(Player entity) throws SQLException {
        if(entity==null || entity.getPlayerID()==-1){
            entity = null;
        }else {
            try(PreparedStatement pst = ConnectionXamp.getConnection().prepareStatement(DELETE)) {
                pst.setInt(1,entity.getPlayerID());
                pst.executeUpdate();
            }
        }
        return entity;
    }
    /**
     * Finds a Player entity by its ID.
     *
     * @param key The ID of the Player to be found.
     * @return The Player entity if found, or null if the ID is invalid or the Player is not found.
     */
    @Override
    public Player findById(Integer key) {
        Player result = new Player();
        if(key==-1) {
            result = null;
        } else {
            try(PreparedStatement pst = ConnectionXamp.getConnection().prepareStatement(FINDBYID)) {
                pst.setInt(1,key);
                ResultSet res = pst.executeQuery();
                if(res.next()){
                    result.setPlayerID(res.getInt("playerID"));
                    result.setName(res.getString("name"));
                    result.setEarnedPoints(res.getInt("earnedPoints"));
                }
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
                LogRead.logWritter(result);
            }
        }
        return result;
    }

    /**
     * Retrieves all Player entities from the database.
     *
     * @return A list of all Player entities.
     */
    @Override
    public List findAll() {
        List<Player> result = new ArrayList<>();

        try(PreparedStatement pst = ConnectionXamp.getConnection().prepareStatement(FINDALL)) {

            ResultSet res = pst.executeQuery();
            while(res.next()){
                Player p = new Player();
                p.setPlayerID(res.getInt("playerID"));
                p.setName(res.getString("name"));
                p.setEarnedPoints(res.getInt("earnedPoints"));
                result.add(p);
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LogRead.logWritter(result);
        }
        return result;
    }

    @Override
    public void close() throws IOException {

    }
    public static PlayerDAO build(){
        return new PlayerDAO();
    }
}
