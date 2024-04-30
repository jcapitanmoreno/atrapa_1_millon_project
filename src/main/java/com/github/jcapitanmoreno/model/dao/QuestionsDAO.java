package com.github.jcapitanmoreno.model.dao;

import com.github.jcapitanmoreno.model.connection.ConnectionMariaDB;
import com.github.jcapitanmoreno.model.entity.Player;
import com.github.jcapitanmoreno.model.entity.Questions;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDAO implements DAO<Questions, String>{

    private final static String INSERT="INSERT INTO questions (questionID, gameID, questionText, answer1, answer2, answer3) VALUES (?,?,?,?,?,?)";
    private final static String UPDATE="UPDATE questions SET questionText=?, answer1=?, answer2=?, answer3=? WHERE questionID=?";
    private final static String FINDALL="SELECT * FROM questions";
    private final static String FINDBYID="SELECT * FROM questions WHERE questionID=?";
    private final static String DELETE="DELETE FROM questions WHERE questionID=?";
    @Override
    public Questions save(Questions entity) {
        Questions result = entity;
        if(entity==null || entity.getQuestionID()==-1) return result;
        Questions q = findById(entity.getQuestionID());  //si no está devuelve un autor por defecto
        if(q.getQuestionID()==-1){
            //INSERT
            try(PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                pst.setInt(1,entity.getQuestionID());
                pst.setInt(2,entity.getGameID());
                pst.setString(3,entity.getQuestionText());

                pst.set(4,entity.getAnswers());
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
                pst.setString(1,entity.getQuestionText());
                pst.setString(2,entity.getDni());
                pst.executeUpdate();

                //update cascada --> opcional
                if(entity.getBooks()!=null){
                    List<Book> booksBefore = BookDAO.build().findByAuthor(entity);
                    List<Book> booksAfter = entity.getBooks();

                    List<Book> booksToBeRemoved = new ArrayList<>(booksBefore);
                    booksToBeRemoved.removeAll(booksAfter);

                    for(Book b:booksToBeRemoved){
                        BookDAO.build().delete(b);
                    }
                    for(Book b:booksAfter){
                        BookDAO.build().save(b);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Questions delete(Questions entity) throws SQLException {
        return null;
    }

    @Override
    public Questions findById(String key) {
        return null;
    }

    @Override
    public List<Questions> findAll() {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
