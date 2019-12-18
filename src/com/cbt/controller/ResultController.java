/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import com.cbt.bll.Result;
import com.cbt.bll.Test;
import com.cbt.dao.TestDao;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
    int count = 0;

    @FXML
    Label testLabel;

    @FXML
    Label testDateLabel;

    @FXML
    Label obtainedMarks;

    @FXML
    Label testStatus;

    @FXML
    Label passMarks;

    @FXML
    Label fullMarks;

    int resultId;

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            try {
                TestDao t = (TestDao) Naming.lookup("rmi://localhost/TestService");
                Result result = t.getResult(resultId);
                testLabel.setText(result.getTest().getTitle());
                testDateLabel.setText(result.getTest().getDate().toString());
                obtainedMarks.setText(Integer.toString(result.getMarks()));
                if (result.getStatus()) {
                    testStatus.setText("PASS");
                    testStatus.setStyle("-fx-text-fill:green");
                } else {
                    testStatus.setText("Failed");
                    testStatus.setStyle("-fx-text-fill:red");
                }

                passMarks.setText(Integer.toString(result.getTest().getPassMarks()));
                fullMarks.setText(Integer.toString(result.getTest().getFullMarks()));

                result.getResultItem().forEach(item -> {
                    if (item.getCorrect()) {
                        makeLayout(item.getQuestion().getTitle(), Integer.toString(item.getQuestion().getMarks()), item.getCorrectAnswer(), item.getSelectedAnswer());
                    } else {
                        makeLayout(item.getQuestion().getTitle(), Integer.toString(item.getQuestion().getMarks()), item.getCorrectAnswer(), item.getSelectedAnswer());

                    }

                });
            } catch (NotBoundException | MalformedURLException | RemoteException ex) {
                Logger.getLogger(ResultController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    @FXML
    private void tryThis() {

        makeLayout("Who is Dhruba", "2", "hero", "hero");
        makeLayout("Who is Dhruba", "2", "don", "hero");
    }

//    @FXML
    private void makeLayout(String qn, String mark, String userAns, String correctAns) {
        count = count + 1;

        Pane p = new Pane();
        Label questionNumber = new Label();
        questionNumber.setFont(Font.font("System", 21));
        questionNumber.setText("" + count);
        questionNumber.setPadding(new Insets(20, 20, 20, 50));

        Label question = new Label();
        question.setFont(Font.font("System", 21));
        question.setText("Question: " + qn);
        question.setPadding(new Insets(20, 20, 20, 50));
        question.setStyle("-fx-font-weight: bold");

        Label marks = new Label();
        marks.setFont(Font.font("System", 21));
        marks.setPadding(new Insets(20, 20, 20, 1100));
        marks.setText("Marks Obtained: " + mark);
        if (userAns.equals(correctAns)) {
            Label option1 = new Label();
            option1.setFont(Font.font("System", 21));
            option1.setPadding(new Insets(140, 20, 20, 100));
            option1.setText("Correct Answer: " + correctAns);

//            Label correct = new Label();
//            correct.setFont(Font.font("System", 21));
//            correct.setPadding(new Insets(140, 20, 20, 800));
//            correct.setText("Correct!");
            p.getChildren().addAll(questionNumber, question, marks, option1);
        } else {
            Label option1 = new Label();
            option1.setFont(Font.font("System", 21));
            option1.setPadding(new Insets(140, 20, 20, 100));
            option1.setText("Correct Answer: " + correctAns);

//            Label correct = new Label();
//            correct.setFont(Font.font("System", 21));
//            correct.setPadding(new Insets(140, 20, 20, 800));
//            correct.setText("Correct!");
            Label option = new Label();
            option.setFont(Font.font("System", 21));
            option.setPadding(new Insets(80, 20, 20, 100));
            option.setText("Submitted Answer: " + userAns);

            Label incorrect = new Label();
            incorrect.setFont(Font.font("System", 21));
            incorrect.setPadding(new Insets(80, 20, 20, 800));
            incorrect.setText("Incorrect!");
            p.getChildren().addAll(questionNumber, question, marks, option, option1, incorrect);

        }

        question.setPadding(new Insets(20, 20, 20, 100));
        p.setStyle("-fx-background-color:white;-fx-background-radius:10px");
        //p.setStyle("-fx-background-color:white";"-fx-background-color:white");

        VBox.setMargin(p, new Insets(40, 40, 5, 50));

        vbox.getChildren().addAll(p);
        System.out.println("Print");
        sp.setContent(vbox);
        sp.setPannable(true);
    }

}
