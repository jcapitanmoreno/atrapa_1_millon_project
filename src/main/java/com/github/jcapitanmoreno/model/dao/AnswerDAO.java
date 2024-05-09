package com.github.jcapitanmoreno.model.dao;

import com.github.jcapitanmoreno.model.connection.ConnectionMariaDB;
import com.github.jcapitanmoreno.model.entity.Answer;
import com.github.jcapitanmoreno.model.entity.Player;
import com.github.jcapitanmoreno.model.entity.Question;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public  class AnswerDAO implements DAO<Answer, String>{

    private final static String INSERT = "INSERT INTO answer (questionID, playerID, time, answerText, validate) VALUES (?,?,?,?,?)";
    private final static String FINDANSWERBYQUESTIONID = "SELECT * FROM `answer` WHERE questionsID=?";
    private final static String FINDANSWERBYPLAYERID = "SELECT * FROM `answer` WHERE playerID=?";
    @Override
    public Answer save(Answer entity) {
        Answer result = entity;
        if (entity == null || entity.getAnswerText() == null){
            result = null;
        } else {
                //INSERT
                try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                    pst.setInt(1, entity.getQuestion().getQuestionID());
                    pst.setInt(2, entity.getPlayer().getPlayerID());
                    pst.setTime(3, java.sql.Time.valueOf(entity.getTime()));
                    pst.setString(4, entity.getAnswerText());
                    pst.setBoolean(5, entity.isValidateAnswer());
                    pst.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return result;
    }



    @Override
    public Answer delete(Answer entity) throws SQLException {
        return null;
    }

    @Override
    public Answer findById(String key) {
        Answer result = new Answer();
        if (key == null){
            result = null;
        } else {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDANSWERBYQUESTIONID)) {
                pst.setString(1, key);
                ResultSet res = pst.executeQuery();
                if (res.next()) {
                    result.setAnswerText(res.getString("answerText"));
                    result.setValidateAnswer(res.getBoolean("validate"));
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
    public Answer findByPlayerId(String key) {
        Answer result = new Answer();
        if (key == null){
            result = null;
        } else {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDANSWERBYPLAYERID)) {
                pst.setString(1, key);
                ResultSet res = pst.executeQuery();
                if (res.next()) {
                    result.setAnswerText(res.getString("answerText"));
                    result.setValidateAnswer(res.getBoolean("validate"));
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
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
