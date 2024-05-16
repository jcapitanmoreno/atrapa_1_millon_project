package com.github.jcapitanmoreno.test;

import com.github.jcapitanmoreno.model.connection.ConnectionProperties;
import com.github.jcapitanmoreno.model.dao.QuestionsDAO;
import com.github.jcapitanmoreno.model.entity.Answer;
import com.github.jcapitanmoreno.model.entity.Game;
import com.github.jcapitanmoreno.model.entity.Player;
import com.github.jcapitanmoreno.model.entity.Question;
import com.github.jcapitanmoreno.utils.XMLManager;

import java.util.ArrayList;
import java.util.List;

public class saveConnection {
    public static void main(String[] args) {
        QuestionsDAO questionsDAO = new QuestionsDAO();
        Game game = new Game();
        List<Answer> answers = new ArrayList<>();
        Player player = new Player();

        Question question = new Question(game, "cuando se descubrio america", answers, player);

        questionsDAO.save(question);
    }
}
