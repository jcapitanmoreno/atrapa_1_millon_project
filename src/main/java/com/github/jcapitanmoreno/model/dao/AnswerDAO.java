package com.github.jcapitanmoreno.model.dao;

import com.github.jcapitanmoreno.model.connection.ConnectionXamp;
import com.github.jcapitanmoreno.model.entity.Answer;
import com.github.jcapitanmoreno.utils.LogRead;

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

    /**
     * Saves an Answer entity to the database. If the entity or its answer text is null, returns null.
     *
     * @param entity The Answer entity to be saved.
     * @return The saved Answer entity, or null if the input entity was null or its answer text was null.
     */
    @Override
    public Answer save(Answer entity) {
        Answer result = entity;
        if (entity == null || entity.getAnswerText() == null){
            result = null;
        } else {
                //INSERT
                try (PreparedStatement pst = ConnectionXamp.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
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

    /**
     * Finds and returns a list of Answer entities based on the given question ID.
     *
     * @param key The ID of the question for which answers are to be found.
     * @return A list of Answer entities associated with the given question ID, or null if the key is -1.
     */
    public List<Answer> findByQuestionID(int key) {
        List<Answer> results = new ArrayList<>();
        if (key == -1) {
            results=null;
        } else {
            try (PreparedStatement pst = ConnectionXamp.getConnection().prepareStatement(FINDANSWERBYQUESTIONID)) {
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
                LogRead.logWritter(results);
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
