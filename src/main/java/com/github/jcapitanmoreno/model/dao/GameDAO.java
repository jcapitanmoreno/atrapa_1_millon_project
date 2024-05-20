package com.github.jcapitanmoreno.model.dao;

import com.github.jcapitanmoreno.model.connection.ConnectionXamp;
import com.github.jcapitanmoreno.model.entity.Game;
import com.github.jcapitanmoreno.utils.LogRead;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GameDAO implements DAO<Game, Integer>{

    private final static String FINDALL = "SELECT * FROM game";
    private final static String FINDBYID="SELECT * FROM game WHERE gameID=?";
    @Override
    public Game save(Game entity) {
        return null;
    }

    @Override
    public Game delete(Game entity) throws SQLException {
        return null;
    }

    /**
     * Finds a game by its ID.
     *
     * @param key The ID of the game to find.
     * @return The Game object if found, otherwise null.
     */
    @Override
    public Game findById(Integer key) {
        Game result = new Game();
        if(key==-1) {
            result = null;
        } else {
            try(PreparedStatement pst = ConnectionXamp.getConnection().prepareStatement(FINDBYID)) {
                pst.setInt(1,key);
                ResultSet res = pst.executeQuery();
                if(res.next()){
                    result.setGameID(res.getInt("gameID"));
                    result.setGameDate(LocalDate.parse(res.getString("gameDate")));
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
     * Retrieves all games from the database.
     *
     * @return A list of all Game objects found in the database.
     */
    @Override
    public List<Game> findAll() {
        List<Game> result = new ArrayList<>();

        try (PreparedStatement pst = ConnectionXamp.getConnection().prepareStatement(FINDALL)) {

            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Game game = new Game();
                game.setGameID(res.getInt("gameID"));
                game.setGameDate(LocalDate.parse(res.getString("gameDate")));
                result.add(game);
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
}
