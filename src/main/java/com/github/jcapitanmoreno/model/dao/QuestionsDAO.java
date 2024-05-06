package com.github.jcapitanmoreno.model.dao;

import com.github.jcapitanmoreno.model.connection.ConnectionMariaDB;
import com.github.jcapitanmoreno.model.entity.Player;
import com.github.jcapitanmoreno.model.entity.Questions;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDAO implements DAO<Questions, Integer> {

    private final static String INSERT = "INSERT INTO questions (questionID, gameID, questionText, answers_1, answers_2, answers_3) VALUES (?,?,?,?,?,?)";
    private final static String UPDATE = "UPDATE questions SET questionText=?, answers_1=?, answers_2=?, answers_3=? WHERE questionID=?";
    private final static String FINDALL = "SELECT * FROM questions";
    private final static String FINDBYID = "SELECT * FROM questions WHERE questionID=?";
    private final static String DELETE = "DELETE FROM questions WHERE questionID=?";

    @Override
    public Questions save(Questions entity) {
        Questions result = entity;
        if (entity == null || entity.getQuestionID() == -1){
            result = null;
        } else {
            Questions q = findById(entity.getQuestionID());  //si no está devuelve un autor por defecto
            if (q.getQuestionID() == -1) {
                //INSERT
                try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                    pst.setInt(1, entity.getQuestionID());
                    pst.setInt(2, entity.getGameID().getGameID());
                    pst.setString(3, entity.getQuestionText());
                    pst.setString(4, entity.getAnswers()[1]);
                    pst.setString(5, entity.getAnswers()[2]);
                    pst.setString(6, entity.getAnswers()[3]);

                    pst.executeUpdate();
                    //si fuera autoincremental yo tendría que leer getGeneratedKeys() -> setDNI
                    ResultSet res = pst.getGeneratedKeys();
                    if(res.next()){
                        entity.setQuestionID(res.getInt(1));
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                //UPDATE
                try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)) {
                    pst.setString(1, entity.getQuestionText());
                    pst.setString(2, entity.getAnswers()[1]);
                    pst.setString(3, entity.getAnswers()[2]);
                    pst.setString(4, entity.getAnswers()[3]);
                    pst.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public Questions delete(Questions entity) throws SQLException {
        if (entity == null || entity.getQuestionID() == -1){
            entity = null;
        }else {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
                pst.setInt(1, entity.getQuestionID());
                pst.executeUpdate();
            }
        }
        return entity;
    }

    @Override
    public Questions findById(Integer key) {
        Questions result = new Questions();
        if (key == -1){
            result = null;
        } else {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYID)) {
                pst.setInt(1, key);
                ResultSet res = pst.executeQuery();
                if (res.next()) {
                    result.setQuestionID(res.getInt("questionID"));
                    result.setQuestionText(res.getString("questionText"));
                    String[] answers = new String[3];
                    answers[0] = res.getString("answers_1");
                    answers[1] = res.getString("answers_2");
                    answers[2] = res.getString("answers_3");
                    result.setAnswers(answers);
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
    public List<Questions> findAll() {
        List<Questions> result = new ArrayList<>();

        try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDALL)) {

            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Questions q = new Questions();
                q.setQuestionID(res.getInt("questionID"));
                q.setQuestionText(res.getString("questionText"));
                String[] answers = new String[3];
                answers[0] = res.getString("answers_1");
                answers[1] = res.getString("answers_2");
                answers[2] = res.getString("answers_3");
                q.setAnswers(answers);
                //Lazy
                // a.setBooks(BookDAO.build().findByAuthor(a));
                result.add(q);
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
