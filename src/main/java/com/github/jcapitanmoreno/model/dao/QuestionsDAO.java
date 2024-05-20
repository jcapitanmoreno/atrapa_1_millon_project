package com.github.jcapitanmoreno.model.dao;

import com.github.jcapitanmoreno.model.connection.ConnectionXamp;
import com.github.jcapitanmoreno.model.entity.Question;
import com.github.jcapitanmoreno.utils.LogRead;

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


    /**
     * Saves a Question entity to the database.
     *
     * @param entity The Question entity to be saved.
     * @return The saved Question entity if successful, or null if the entity is invalid or not saved.
     */
    @Override
    public Question save(Question entity) {
        Question result = entity;
        if (entity == null || entity.getQuestionID() == -1) {
            result = null;
        } else {
            Question q = findById(entity.getQuestionID());
            if (q.getQuestionID() != -1) {
                //INSERT
                try (PreparedStatement pst = ConnectionXamp.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
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
                    LogRead.logWritter(result);
                }
            }
        }
        return result;
    }
    /**
     * Updates a Question entity in the database.
     *
     * @param entity The Question entity to be updated.
     * @return The updated Question entity if successful, or null if the entity is invalid or not updated.
     */
    public Question update(Question entity) {
        Question result = entity;
        if (entity == null || entity.getQuestionID() == -1) {
            result = null;
        } else {
            //UPDATE
            try (PreparedStatement pst = ConnectionXamp.getConnection().prepareStatement(UPDATE)) {
                pst.setString(1, entity.getQuestionText());
                pst.setInt(2, entity.getQuestionID());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                LogRead.logWritter(result);
            }
        }
        return result;
    }

    /**
     * Deletes a Question entity from the database.
     *
     * @param entity The Question entity to be deleted.
     * @return The deleted Question entity if successful, or null if the entity is invalid or not deleted.
     * @throws SQLException If an SQL exception occurs during the deletion process.
     */
    @Override
    public Question delete(Question entity) throws SQLException {
        if (entity == null || entity.getQuestionID() == -1) {
            entity = null;
        } else {
            try (PreparedStatement pst = ConnectionXamp.getConnection().prepareStatement(DELETE)) {
                pst.setInt(1, entity.getQuestionID());
                pst.executeUpdate();
            }
        }
        return entity;
    }

    /**
     * Finds a Question entity by its ID in the database.
     *
     * @param key The ID of the Question entity to find.
     * @return The Question entity found with the given ID, or null if not found or key is invalid.
     */
    @Override
    public Question findById(Integer key) {
        Question result = new Question();
        if (key == -1) {
            result = null;
        } else {
            try (PreparedStatement pst = ConnectionXamp.getConnection().prepareStatement(FINDBYID)) {
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

    /**
     * Retrieves all Question entities from the database.
     *
     * @return A list containing all Question entities retrieved from the database.
     */
    @Override
    public List<Question> findAll() {
        List<Question> result = new ArrayList<>();

        try (PreparedStatement pst = ConnectionXamp.getConnection().prepareStatement(FINDALL)) {

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

    /**
     * Counts the total number of questions in the database.
     *
     * @return The total number of questions in the database.
     */
    public int countQuestions() {
        int count = 0;
        try (PreparedStatement pst = ConnectionXamp.getConnection().prepareStatement(COUNT_QUESTIONS)) {
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
