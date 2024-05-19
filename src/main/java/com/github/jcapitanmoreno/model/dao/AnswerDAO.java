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
import java.util.ArrayList;
import java.util.List;

public  class AnswerDAO implements DAO<Answer, String>{

    private final static String INSERT = "INSERT INTO answer (answerText, validate, questionsID, playerID) VALUES (?,?,?,?)";
    private final static String FINDANSWERBYQUESTIONID = "SELECT * FROM `answer` WHERE questionsID=?";
    private final static String FINDANSWERBYPLAYERID = "SELECT * FROM `answer` WHERE playerID=?";
    private final static String FINDANSWERBYQUESTION = "SELECT a.* FROM answer a, questions q WHERE a.questionsID=q.questionsID AND questionsID=?";
    @Override
    public Answer save(Answer entity) {
        Answer result = entity;
        if (entity == null || entity.getAnswerText() == null){
            result = null;
        } else {
                //INSERT
                try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                    pst.setString(1, entity.getAnswerText());
                    pst.setBoolean(2, entity.isValidateAnswer());
                    pst.setInt(3, entity.getQuestionsID().getQuestionID());
                    pst.setInt(4, entity.getPlayerID().getPlayerID());
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
        return null;
    }

    public List<Answer> findByQuestionID(int key) {
        List<Answer> results = new ArrayList<>();
        if (key == -1) {
            results=null;
        } else {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDANSWERBYQUESTIONID)) {
                pst.setInt(1, key);
                ResultSet res = pst.executeQuery();
                while (res.next()) {
                    Answer answer = new Answer();
                    answer.setAnswerText(res.getString("answerText"));
                    answer.setValidateAnswer(res.getBoolean("validate"));
                    results.add(answer);
                }
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
