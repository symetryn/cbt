/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import com.cbt.model.Answer;
import com.cbt.model.AnswerGroup;
import com.cbt.model.Question;
import com.cbt.model.Result;
import com.cbt.model.ResultItem;
import com.cbt.model.Test;
import com.cbt.dao.TestDao;
import com.cbt.utils.Router;
import com.cbt.utils.UserState;
import com.google.gson.Gson;
import com.jfoenix.controls.JFXProgressBar;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import static javafx.collections.FXCollections.shuffle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 *
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
    Label time;

    @FXML
    Button nextBtn;

    @FXML
    Button prevBtn;

    @FXML
    JFXProgressBar progressBar;

    Test test;

    Test clientTest;

    int testId;

    int seed;

    int currentQuestionIndex = 0;

    Question currentQuestion;

    ArrayList<AnswerGroup> answerList;

    /**
     *
     * @param testId to set the testId
     */
    public void setTestId(int testId) {
        this.testId = testId;
    }

    /**
     * to initialize the constructor
     */
    public StudentExamController() {
        answerList = new ArrayList<>();
        Random r = new Random();
        seed=r.nextInt();

    }

    /**
     * Initializes the controller class.

     * @param url The url used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The rb used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserState user = UserState.getInstance();
        user.getName();

        Timer timer = new Timer();

        Platform.runLater(() -> {
            try {
                TestDao t = (TestDao) Naming.lookup("rmi://localhost/TestService");
                test = t.getTest(testId);
                clientTest = t.getTest(testId);
                clientTest.setQuestions(new ArrayList<>());
                testField.setText(test.getTitle());

                //random questions
                ArrayList<Question> randomQuestions = test.getQuestions();
                Collections.shuffle(randomQuestions, new Random(seed));
                test.setQuestions(randomQuestions);

                fullMarksField.setText(Integer.toString(test.getFullMarks()));
                passMarksField.setText(Integer.toString(test.getPassMarks()));
                durationField.setText(Integer.toString(test.getDuration()));
                createQuestionPanel();
                timer.scheduleAtFixedRate(new TimerTask() {
                    int interval = test.getDuration() * 60;
                    int total = test.getDuration() * 60;

                    @Override
                    public void run() {

                        if (interval > 0) {
                            Platform.runLater(() -> {
                                time.setText(LocalTime.MIN.plusSeconds(interval).toString());
                                progressBar.setProgress((double) (total - interval) / total);
                                System.out.println(progressBar.getProgress());
                                System.out.println("total" + total);
                                System.out.println("interval" + interval);
                                interval--;

                            });
//                            System.out.println(interval);

                        } else {
                            submitTest();
                            timer.cancel();
                        }
                    }
                }, 1000, 1000);

            } catch (NotBoundException | MalformedURLException | RemoteException ex) {
                Logger.getLogger(StudentExamController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    /**
     * To create the question panel with various label and button
     */
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
        ToggleGroup radioGroup = new ToggleGroup();
        if (clientTest.getQuestions().contains(currentQuestion)) {
//            currentQuestion = clientTest.getQuestions().get(currentQuestionIndex);

            for (Answer a : currentQuestion.getAnswers()) {
                System.out.println("previous answer called");

                createOptionRow(a.getTitle(), a.getCorrectAnswer(), radioGroup);
            }

        } else {

            for (Answer a : currentQuestion.getAnswers()) {
                System.out.println("new called");
                createOptionRow(a.getTitle(), false, radioGroup);

            }

        }

        sp.setContent(option);
    }

    @FXML
    private void nextQuestion() {
        if (currentQuestionIndex != (test.getQuestions().size() - 1)) {

            updateAnswerList();

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

            ArrayList<Question> randomQuestions = newTest.getQuestions();
            Collections.shuffle(randomQuestions, new Random(seed));
            newTest.setQuestions(randomQuestions);
            updateAnswerList();

            Result r = new Result();

            ArrayList<Question> actualQuestions = newTest.getQuestions();
            ArrayList<Question> clientQuestions = clientTest.getQuestions();

            for (int i = 0; i < actualQuestions.size(); i++) {
                Answer correctAnswer = new Answer();
                Answer selectedAnswer = new Answer();
                System.out.println("question-----------");

                //get actual answer
                for (Answer a : actualQuestions.get(i).getAnswers()) {
                    System.out.println(a.getTitle() + "  " + a.getCorrectAnswer());
                    if (a.getCorrectAnswer() == true) {
                        correctAnswer = a;
                        System.out.println("Correct" + a.getTitle());
                        break;
                    }
                }
                //get submitted answer
                System.out.println("client--------------");
                if (clientQuestions.size() > i) {
                    for (Answer an : clientQuestions.get(i).getAnswers()) {
                        System.out.println(an.getTitle() + "  " + an.getCorrectAnswer());
                        if (an.getCorrectAnswer() == true) {
                            selectedAnswer = an;
                            System.out.println("Client answer" + an.getTitle());
                            break;
                        }

                    }
                }
                Boolean selectedCorrectAnswer = (selectedAnswer.getTitle() != null) ? selectedAnswer.getTitle().equals(correctAnswer.getTitle()) : false;

                if (selectedCorrectAnswer) {
                    r.addMarks(actualQuestions.get(i).getMarks());

                }
                r.pushResultItem(new ResultItem(actualQuestions.get(i).getId(), correctAnswer.getTitle(), selectedAnswer.getTitle(), selectedCorrectAnswer));
            }
            if (r.getMarks() >= newTest.getPassMarks()) {
                r.setStatus(true);
            } else {
                r.setStatus(false);
            }
            r.setTestId(test.getId());
            r.setUserId(UserState.getInstance().getUserId());
            Integer resultID = t.saveResult(r);
            Router router = new Router();
            router.routeToResult(resultID);

            System.out.println(r.toString());
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(StudentExamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void prevQuestion() {

        updateAnswerList();
        if (currentQuestionIndex > 0) {
            System.out.println("change question Index from " + currentQuestionIndex);
            currentQuestionIndex--;
            System.out.println("to " + currentQuestionIndex);

            createQuestionPanel();
        }

    }

    private void updateAnswerList() {
        ArrayList<Answer> a = new ArrayList<>();
        for (AnswerGroup ag : answerList) {
            a.add(ag.getAnswer());
            System.out.println(ag.getAnswer().getTitle());
        }
        currentQuestion.setAnswers(a);

        clientTest.pushQuestion(currentQuestion);
    }

    private void createOptionRow(String optionText, Boolean selectedStatus, ToggleGroup t) {
        Label options = new Label();
        HBox hbox = new HBox();
        RadioButton check = new RadioButton();
        options.setFont(Font.font("System", 21));
        options.setText(optionText);
        check.setSelected(selectedStatus);
        hbox.getChildren().addAll(check, options);
        option.setSpacing(30);
        hbox.setSpacing(20);
        hbox.setAlignment(Pos.CENTER_LEFT);
        option.getChildren().addAll(hbox);
        check.setToggleGroup(t);

        options.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            check.setSelected(!check.isSelected());
        });
        answerList.add(new AnswerGroup(check, options));

    }

}
