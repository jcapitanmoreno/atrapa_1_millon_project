package com.github.jcapitanmoreno.model.dao;

import com.github.jcapitanmoreno.model.connection.ConnectionMariaDB;
import com.github.jcapitanmoreno.model.entity.Answer;
import com.github.jcapitanmoreno.model.entity.Game;
import com.github.jcapitanmoreno.model.entity.Question;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDAO implements DAO<Question, Integer> {

    private final static String INSERT = "INSERT INTO questions (gameID, questionText) VALUES (?,?)";
    private final static String UPDATE = "UPDATE questions SET questionText=? WHERE questionID=?";
    private final static String FINDALL = "SELECT * FROM questions";
    private final static String FINDBYID = "SELECT * FROM questions WHERE questionID=?";
    private final static String DELETE = "DELETE FROM questions WHERE questionID=?";
    private final static String COUNT_QUESTIONS = "SELECT COUNT(*) AS total FROM questions";


    @Override
    public Question save(Question entity) {
        Question result = entity;
        if (entity == null || entity.getQuestionID() == -1) {
            result = null;
        } else {
            Question q = findById(entity.getQuestionID());
            if (q.getQuestionID() != -1) {
                //INSERT
                try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                    pst.setInt(1, entity.getGame().getGameID());
                    pst.setString(2, entity.getQuestionText());
                    pst.executeUpdate();
                    ResultSet res = pst.getGeneratedKeys();
                    if (res.next()) {
                        int generatedID = res.getInt(1);
                        entity.setQuestionID(generatedID);
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public Question update(Question entity) {
        Question result = entity;
        if (entity == null || entity.getQuestionID() == -1) {
            result = null;
        } else {
            //UPDATE
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)) {
                pst.setString(1, entity.getQuestionText());
                pst.setInt(2, entity.getQuestionID());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Question delete(Question entity) throws SQLException {
        if (entity == null || entity.getQuestionID() == -1) {
            entity = null;
        } else {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
                pst.setInt(1, entity.getQuestionID());
                pst.executeUpdate();
            }
        }
        return entity;
    }

    @Override
    public Question findById(Integer key) {
        Question result = new Question();
        if (key == -1) {
            result = null;
        } else {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYID)) {
                pst.setInt(1, key);
                ResultSet res = pst.executeQuery();
                if (res.next()) {
                    result.setQuestionID(res.getInt("questionID"));
                    result.setQuestionText(res.getString("questionText"));
                }
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public List<Question> findAll() {
        List<Question> result = new ArrayList<>();

        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDALL)) {

            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Question q = new Question();
                q.setQuestionID(res.getInt("questionID"));
                q.setQuestionText(res.getString("questionText"));

                result.add(q);
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int countQuestions() {
        int count = 0;
        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(COUNT_QUESTIONS)) {
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                count = res.getInt("total");
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void close() throws IOException {

    }

    public static QuestionsDAO build() {
        return new QuestionsDAO();
    }
}
