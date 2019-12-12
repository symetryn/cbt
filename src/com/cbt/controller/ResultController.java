/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ResultController implements Initializable {

    @FXML
    ScrollPane sp;
    VBox vbox = new VBox();
    int count = 1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void tryThis() {

        makeLayout(count, "Who is Dhruba", "2", "hero", "hero");
        makeLayout(count, "Who is Dhruba", "2", "don", "hero");
    }

    @FXML
    private void makeLayout(int qnNo, String qn, String mark, String userAns, String correctAns) {
        count = count + 1;

        Pane p = new Pane();
        Label questionNumber = new Label();
        questionNumber.setFont(Font.font("System", 21));
        questionNumber.setText("" + qnNo);
        questionNumber.setPadding(new Insets(20, 20, 20, 50));

        Label question = new Label();
        question.setFont(Font.font("System", 21));
        question.setText(qn);
        question.setPadding(new Insets(20, 20, 20, 50));

        Label marks = new Label();
        marks.setFont(Font.font("System", 21));
        marks.setPadding(new Insets(20, 20, 20, 1300));
        marks.setText(mark);
        if (userAns.equals(correctAns)) {
            Label option1 = new Label();
            option1.setFont(Font.font("System", 21));
            option1.setPadding(new Insets(140, 20, 20, 200));
            option1.setText(correctAns);

            Label correct = new Label();
            correct.setFont(Font.font("System", 21));
            correct.setPadding(new Insets(140, 20, 20, 800));
            correct.setText("Correct!");
            p.getChildren().addAll(questionNumber, question, marks, option1, correct);
        } else {
            Label option1 = new Label();
            option1.setFont(Font.font("System", 21));
            option1.setPadding(new Insets(140, 20, 20, 200));
            option1.setText(correctAns);

            Label correct = new Label();
            correct.setFont(Font.font("System", 21));
            correct.setPadding(new Insets(140, 20, 20, 800));
            correct.setText("Correct!");

            Label option = new Label();
            option.setFont(Font.font("System", 21));
            option.setPadding(new Insets(80, 20, 20, 200));
            option.setText(userAns);

            Label incorrect = new Label();
            incorrect.setFont(Font.font("System", 21));
            incorrect.setPadding(new Insets(80, 20, 20, 800));
            incorrect.setText("Incorrect!");
            p.getChildren().addAll(questionNumber, question, marks, option, option1, correct, incorrect);

        }

        question.setPadding(new Insets(20, 20, 20, 200));
        p.setStyle("-fx-background-color:white;-fx-background-radius:10px");
        //p.setStyle("-fx-background-color:white";"-fx-background-color:white");

        VBox.setMargin(p, new Insets(40, 40, 5, 50));

        vbox.getChildren().addAll(p);
        System.out.println("Print");
        sp.setContent(vbox);
        sp.setPannable(true);
    }

}
