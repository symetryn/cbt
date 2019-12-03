/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import com.cbt.bll.Answer;
import com.cbt.bll.OptionGroup;
import com.cbt.bll.Question;
import com.cbt.bll.Test;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.JFXTreeTableView;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AddQuestionController implements Initializable {

    int count = 2;
    int yAxis = 85;

    ArrayList<OptionGroup> optionList;

    /**
     * Initializes the controller class.
     */
    @FXML
    private ScrollPane scrollpane;

    @FXML
    private Pane pane;

    @FXML
    private JFXTimePicker startTime;

    @FXML
    private TableView<Question> questionTable;

    @FXML
    private TableColumn<?, ?> questionColumn;

    @FXML
    private TableColumn<?, ?> answerColumn;

    @FXML
    private TableColumn<?, ?> marksColumn;

    @FXML
    private TextArea questionField;

    @FXML
    private TextField marksField;

    Test test;

    public AddQuestionController() {
        test = new Test();
        optionList = new ArrayList<OptionGroup>();
    }

    @FXML
    private void addOptionClick(ActionEvent e) throws Exception {
        createQuestionRow();

    }

    private void createQuestionRow() {
        TextField option = new TextField();
        Button drop = new Button("Drop");
        CheckBox correct = new CheckBox();

        option.setLayoutX(11);
        option.setLayoutY(yAxis);
        option.setPrefSize(633, 42);
//        option.setId("option" + count);
        option.setPromptText("Option ");

        drop.setLayoutX(653);
        drop.setLayoutY(yAxis);
        drop.setPrefSize(65, 33);
        drop.setStyle("-fx-background-color:#525A65");
        drop.setStyle("-fx-text-fill:#ffffff");
//        drop.setId("drop" + count);

        correct.setLayoutX(732);
        correct.setLayoutY(yAxis + 7);
//        correct.setId("correct" + count);

        count += 1;
        yAxis += 68;

        pane.getChildren().addAll(option, drop, correct);
        scrollpane.setContent(pane);
        scrollpane.setPannable(true);

        OptionGroup og = new OptionGroup(option, drop, correct);

        optionList.add(og);

    }

    @FXML
    private void saveQuestion() {
//        optionList = new ArrayList<OptionGroup>();
//         yAxis = optionList.size() * 60 + 85;
        Question q = new Question();
        q.setTitle(questionField.getText());
        q.setMarks(Integer.parseInt(marksField.getText()));

//        int textCount = 0;
//        int checkCount = 0;
        optionList.forEach(data -> {
            String text = data.getTextField().getText();
            System.out.println("before " + text);

            if (text != null && !text.isEmpty()) {
                System.out.println("after:" + text);
                Answer a = new Answer(text, data.getBox().isSelected());
                q.addAnswer(a);
            }
        });

//        for (Node node : pane.getChildren()) {
//
//            if (node instanceof TextField && node.getId()==null) {
//                System.out.println(((TextField) node).getText()+ " here");
//
//                if (((TextField) node).getText() == "" || ((TextField) node).getText() == null) {
//                    break;
//                }
//                a.setTitle(((TextField) node).getText());
//                textCount++;
//
//            } else if (node instanceof CheckBox) {
//                a.setCorrectAnswer(((CheckBox) node).isSelected());
//                checkCount++;
//            }
////            System.out.println(textCount + "   " + checkCount);
//            if (textCount == 1 && checkCount == 1) {
//                System.out.println("selected");
//                System.out.print(a.getTitle());
//                if (a.getTitle() != null && a.getTitle().trim() != "") {
//                    q.addAnswer(a);
//                }
//                a = new Answer();
//                textCount = 0;
//                checkCount = 0;
//            }
//
//        }
////        ArrayList ar = new ArrayList();
//        Answer a = new Answer("this is answer", true);
////        ar.add(a);
//////        q.setAnswers();
//
//        q.addAnswer(a);
//        q.addAnswer(a);
//        q.addAnswer(a);
        test.pushQuestion(q);
        setTable();

        reset();
    }

    private void setTable() {
        ObservableList questions = FXCollections.observableArrayList(test.getQuestions());

        questionTable.setItems(questions);
    }

    private void reset() {
        questionField.clear();
//        pane.getChildren().clear();
        for (Node node : pane.getChildren()) {

            if (node instanceof TextField) {
                ((TextField) node).clear();

            }
            if (node instanceof CheckBox) {
                ((CheckBox) node).setSelected(false);
            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createQuestionRow();
        createQuestionRow();

        questionColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        marksColumn.setCellValueFactory(new PropertyValueFactory<>("marks"));

        questionTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            if (newSelection != null & newSelection != oldSelection) {

                questionField.setText((newSelection.getTitle()));
                marksField.setText(Integer.toString(newSelection.getMarks()));

//                pane.getChildren().removeIf(node -> node.getId() == null);
////                pane.getChildren().removeIf(node -> node instanceof CheckBox);
////                pane.getChildren().removeIf(node -> {
////                    if(node.getId()==null) return node instanceof Button;
////                    return (node instanceof Button && node.getId().toString()!="addOption") ;
////                            });
                ArrayList<Answer> newAnswerList = newSelection.getAnswers();
                int optionSize = optionList.size();
                int answerSize = newAnswerList.size();
                

                System.out.println(optionSize);
                System.out.println(answerSize);

                if (optionSize < answerSize) {
                    int difference = answerSize - optionSize;
                    System.out.println("difference " + difference);
                    for (int i = 0; i < difference; i++) {
                        createQuestionRow();
                    }
                }

                if (optionSize > answerSize) {
                    int difference = optionSize - answerSize;
                    System.out.println("difference " + difference);
                    for (int i = difference; i > 0; i--) {
//                        if (optionSize == answerSize) {
//                            break;
//                        }
                        pane.getChildren().remove(optionList.get(optionList.size() - 1).getBox());
                        pane.getChildren().remove(optionList.get(optionList.size() - 1).getTextField());
                        pane.getChildren().remove(optionList.get(optionList.size() - 1).getBtn());
                        optionList.remove(optionList.size() - 1);

                    }
                }
                optionSize = optionList.size();
                answerSize = newAnswerList.size();
                yAxis = optionSize * 60 + 85;
//                System.out.println(optionSize);
//                System.out.println(answerSize);
//                optionList = new ArrayList<OptionGroup>();
                for (int i = 0; i < answerSize; i++) {
                    //set option

                    TextField optionTitle = optionList.get(i).getTextField();
                    optionTitle.setText(newAnswerList.get(i).getTitle());

                    //set correct answer
                    CheckBox optionCheck = optionList.get(i).getBox();
                    optionCheck.setSelected(newAnswerList.get(i).getCorrectAnswer());

                }

//                System.out.println(newSelection.getAnswers().size());
//                newSelection.getAnswers().forEach((data) -> {
////                    System.out.println("therre");
//                    System.out.println(data.getTitle());
////                    System.out.println(data.getCorrectAnswer());
//                    createQuestionRow();
//                });
            }
        });

    }

}
