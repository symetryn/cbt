/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import com.cbt.bll.Answer;
import com.cbt.bll.AnswerGroup;
import com.cbt.bll.Question;
import com.cbt.bll.Result;
import com.cbt.bll.ResultItem;
import com.cbt.bll.Test;
import com.cbt.dao.TestDao;
import com.cbt.utils.UserState;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Dhruba
 */
public class StudentExamController implements Initializable {

    @FXML
    ScrollPane sp;
    @FXML
    VBox option;

    @FXML
    Label testField;

    @FXML
    Label fullMarksField;

    @FXML
    Label passMarksField;

    @FXML
    Label durationField;

    @FXML
    Label question;

    @FXML
    Label marks;

    @FXML
    Label questionNumber;

    @FXML
    Button nextBtn;

    @FXML
    Button prevBtn;

    Test test;

    Test clientTest;

    int testId;

    int currentQuestionIndex = 0;

    Question currentQuestion;

    ArrayList<AnswerGroup> answerList;

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public StudentExamController() {
        answerList = new ArrayList<>();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserState user = UserState.getInstance();
        user.getName();
        Platform.runLater(() -> {
            try {
                TestDao t = (TestDao) Naming.lookup("rmi://localhost/TestService");
                test = t.getTest(testId);
                clientTest = t.getTest(testId);
                clientTest.setQuestions(new ArrayList<>());
                testField.setText(test.getTitle());

                fullMarksField.setText(Integer.toString(test.getFullMarks()));
                passMarksField.setText(Integer.toString(test.getPassMarks()));
                durationField.setText(Integer.toString(test.getDuration()));
                createQuestionPanel();

            } catch (NotBoundException | MalformedURLException | RemoteException ex) {
                Logger.getLogger(StudentExamController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    @FXML
    public void createQuestionPanel() {
        answerList = new ArrayList<>();
        prevBtn.setVisible(true);
        option.getChildren().clear();
        nextBtn.setText("Next");
        nextBtn.setStyle("-fx-background-color: #525A65");
        System.out.println(test.getTitle());
        System.out.println(test.getQuestions().size());
        currentQuestion = test.getQuestions().get(currentQuestionIndex);

        questionNumber.setText(Integer.toString(currentQuestionIndex + 1));
        question.setText(currentQuestion.getTitle());
        marks.setText(Integer.toString(currentQuestion.getMarks()));

        Boolean lastQuestion = (currentQuestionIndex == test.getQuestions().size() - 1);
        if (currentQuestionIndex == 0) {
            prevBtn.setVisible(false);

        }
        if (lastQuestion) {

            nextBtn.setText("Submit");
            nextBtn.setStyle("-fx-background-color:red");

        }
        System.out.println(currentQuestionIndex);
        System.out.println(clientTest.getQuestions().size());
        if (clientTest.getQuestions().contains(currentQuestion)) {
//            currentQuestion = clientTest.getQuestions().get(currentQuestionIndex);
            for (Answer a : currentQuestion.getAnswers()) {
                System.out.println("previous answer called");
                createOptionRow(a.getTitle(), a.getCorrectAnswer());
            }

        } else {

            for (Answer a : currentQuestion.getAnswers()) {
                System.out.println("new called");
                createOptionRow(a.getTitle(), false);

            }

        }

        sp.setContent(option);
    }

    @FXML
    private void nextQuestion() {
        if (currentQuestionIndex != (test.getQuestions().size() - 1)) {

            System.out.println(test.getQuestions().size() - 1);
            System.out.println(currentQuestionIndex);
            ArrayList<Answer> a = new ArrayList<>();
            for (AnswerGroup ag : answerList) {
                a.add(ag.getAnswer());
                System.out.println(ag.getAnswer().getTitle());
            }
            currentQuestion.setAnswers(a);
            clientTest.pushQuestion(currentQuestion);
            currentQuestionIndex++;
            createQuestionPanel();
        } else {
            submitTest();
        }

    }

    private void submitTest() {
        try {
            TestDao t = (TestDao) Naming.lookup("rmi://localhost/TestService");
            Test newTest = t.getTest(testId);
            ArrayList<Answer> ans = new ArrayList<>();
            for (AnswerGroup ag : answerList) {
                ans.add(ag.getAnswer());
                System.out.println(ag.getAnswer().getTitle());
            }
            currentQuestion.setAnswers(ans);
            clientTest.pushQuestion(currentQuestion);
            System.out.println("test submitted");
            Result r = new Result();
            System.out.println(newTest.toString());

            System.out.println(clientTest.toString());
            ArrayList<Question> actualQuestions = newTest.getQuestions();
            ArrayList<Question> clientQuestions = clientTest.getQuestions();
            for (int i = 0; i < actualQuestions.size(); i++) {
//            ArrayList<Answer> correctAnswer = actualQuestions.get(i).getAnswers().stream().filter(data -> {
//                return data.getCorrectAnswer() == true;
//            }).collect(Collectors.toCollection(ArrayList::new));
//            ArrayList<Answer> selectedAnswer = actualQuestions.get(i).getAnswers().stream().filter(data -> {
//                return data.getCorrectAnswer() == true;
//            }).collect(Collectors.toCollection(ArrayList::new));
//            System.out.println("selected" + correctAnswer.get(0));
//            System.out.println("selected" + correctAnswer.get(0));
                Answer correctAnswer = new Answer();
                Answer selectedAnswer = new Answer();
                System.out.println("question-----------");
                for (Answer a : actualQuestions.get(i).getAnswers()) {
                    System.out.println(a.getTitle() + "  " + a.getCorrectAnswer());
                    if (a.getCorrectAnswer() == true) {
                        correctAnswer = a;
                        System.out.println("Correct" + a.getTitle());
                        break;
                    }
                }
                System.out.println("client--------------");
                for (Answer an : clientQuestions.get(i).getAnswers()) {
                    System.out.println(an.getTitle() + "  " + an.getCorrectAnswer());
                    if (an.getCorrectAnswer() == true) {
                        selectedAnswer = an;
                        System.out.println("Client answer" + an.getTitle());
                        break;
                    }
                }
                Boolean selectedCorrectAnswer = selectedAnswer.getTitle().equals(correctAnswer.getTitle());

                if (selectedCorrectAnswer) {
                    r.addMarks(actualQuestions.get(i).getMarks());
                    r.pushResultItem(new ResultItem(actualQuestions.get(i).getTitle(), correctAnswer.getTitle(), selectedAnswer.getTitle(), true));
                } else {
                    r.pushResultItem(new ResultItem(actualQuestions.get(i).getTitle(), correctAnswer.getTitle(), selectedAnswer.getTitle(), false));

                }

            }
            System.out.println(r.toString());
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(StudentExamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void prevQuestion() {
        ArrayList<Answer> a = new ArrayList<>();
        for (AnswerGroup ag : answerList) {
            a.add(ag.getAnswer());
            System.out.println(ag.getAnswer().getTitle());
        }
        currentQuestion.setAnswers(a);

        clientTest.pushQuestion(currentQuestion);
        if (currentQuestionIndex > 0) {
            System.out.println("change question Index from " + currentQuestionIndex);
            currentQuestionIndex--;
            System.out.println("to " + currentQuestionIndex);

            createQuestionPanel();
        }

    }

    private void createOptionRow(String optionText, Boolean selectedStatus) {
        Label options = new Label();
        HBox hbox = new HBox();
        CheckBox check = new CheckBox();
        options.setFont(Font.font("System", 21));
        options.setText(optionText);
        check.setSelected(selectedStatus);
        hbox.getChildren().addAll(check, options);
        option.setSpacing(30);
        hbox.setSpacing(20);
        hbox.setAlignment(Pos.CENTER);
        option.getChildren().addAll(hbox);
        answerList.add(new AnswerGroup(check, options));

    }

}
