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
import com.cbt.dao.TestDao;
import com.jfoenix.controls.JFXTimePicker;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ViewExamController implements Initializable {

    int count = 2;
    int yAxis = 85;

    ArrayList<OptionGroup> optionList;

    ObservableList questions;

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

    @FXML
    private TextField titleField;

    @FXML
    private ComboBox levelDrop;

    @FXML
    private ComboBox semesterDrop;

    @FXML
    private TextField durationField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private JFXTimePicker endTime;

    @FXML
    DatePicker testDate;

    @FXML
    private TextField passMarksField;

    @FXML
    private Button updateBtn;

    Test test;

    int selectedItem;

    public ViewExamController() {
        test = new Test();
        optionList = new ArrayList<OptionGroup>();
    }

    public void setTest(int testId) {
        test.setId(testId);
        System.out.println("testid set" + testId);
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
        option.setPromptText("Option ");

        drop.setLayoutX(653);
        drop.setLayoutY(yAxis);
        drop.setPrefSize(65, 33);
        drop.setStyle("-fx-background-color:#525A65;-fx-text-fill:#ffffff");

        OptionGroup og = new OptionGroup(option, drop, correct);
        drop.setOnAction((ActionEvent e) -> {
            dropItem(og);
        });

        // drop.setId("drop" + count);
        correct.setLayoutX(732);
        correct.setLayoutY(yAxis + 7);
        // correct.setId("correct" + count);

        count += 1;
        yAxis += 60;

        pane.getChildren().addAll(option, drop, correct);
        scrollpane.setContent(pane);
        scrollpane.setPannable(true);

        optionList.add(og);

    }

    private void dropItem(OptionGroup og) {
        yAxis = 85 + 60 * (optionList.size() - 1);
        pane.getChildren().remove(og.getTextField());
        pane.getChildren().remove(og.getBox());
        pane.getChildren().remove(og.getBtn());
        int removedIndex = optionList.indexOf(og);
        optionList.remove(og);

        if (removedIndex != optionList.size()) {
            for (int i = removedIndex; i < optionList.size(); i++) {

                OptionGroup currentOption = optionList.get(i);
                double previous = currentOption.getTextField().getLayoutY();
                currentOption.getTextField().setLayoutY(previous - 60);
                currentOption.getBtn().setLayoutY(previous - 58);
                currentOption.getBox().setLayoutY(previous - 53);
            }
        }
        System.out.println(optionList.size());

    }

    @FXML
    private void saveQuestion() {
        // optionList = new ArrayList<OptionGroup>();
        // yAxis = optionList.size() * 60 + 85;
        Question q = new Question();
        q.setTitle(questionField.getText());
        q.setMarks(Integer.parseInt(marksField.getText()));

        // int textCount = 0;
        // int checkCount = 0;
        optionList.forEach(data -> {
            String text = data.getTextField().getText();
            System.out.println("before " + text);

            if (text != null && !text.isEmpty()) {
                System.out.println("after:" + text);
                Answer a = new Answer(text, data.getBox().isSelected());
                q.addAnswer(a);
            }
        });

        test.pushQuestion(q);
        setTable();

        reset();
    }

    private void setTable() {
        System.out.println("set" + test.getQuestions().size() + " questions");
        questions = FXCollections.observableArrayList(test.getQuestions());

        questionTable.setItems(questions);
    }

    private void reset() {
        questionField.clear();
        // pane.getChildren().clear();
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
        // initalize level dropdown
        levelDrop.getItems().removeAll(levelDrop.getItems());
        levelDrop.getItems().addAll(4, 5, 6);

        // initalize semester dropdown
        semesterDrop.getItems().removeAll(levelDrop.getItems());
        semesterDrop.getItems().addAll(1, 2);
        createQuestionRow();
        createQuestionRow();

        questionColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        marksColumn.setCellValueFactory(new PropertyValueFactory<>("marks"));

        initalizeTableEvent();

        Platform.runLater(() -> {
            try {
                TestDao t = (TestDao) Naming.lookup("rmi://localhost/TestService");

                test = t.getTest(test.getId());
                titleField.setText(test.getTitle());
                passwordField.setText(test.getPassword());
                startTime.setValue(test.getStartTime().toLocalTime());
                endTime.setValue(test.getEndTime().toLocalTime());
                durationField.setText(Integer.toString(test.getDuration()));
                levelDrop.getSelectionModel().select(test.getLevel());
                semesterDrop.getSelectionModel().select(test.getSemester());
                testDate.setValue(test.getDate().toLocalDate());
                passMarksField.setText(Integer.toString(test.getPassMarks()));

                setTable();

            } catch (NotBoundException | MalformedURLException | RemoteException ex) {
                Logger.getLogger(ViewExamController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }

    private void initalizeTableEvent() {
        questionTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            if (newSelection != null & newSelection != oldSelection) {

                questionField.setText((newSelection.getTitle()));
                marksField.setText(Integer.toString(newSelection.getMarks()));

                ArrayList<Answer> newAnswerList = newSelection.getAnswers();

                selectedItem = test.getQuestions().indexOf(newSelection);
                System.out.println("selected Item" + selectedItem);

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

                        pane.getChildren().remove(optionList.get(optionList.size() - 1).getBox());
                        pane.getChildren().remove(optionList.get(optionList.size() - 1).getTextField());
                        pane.getChildren().remove(optionList.get(optionList.size() - 1).getBtn());
                        optionList.remove(optionList.size() - 1);

                    }
                }
                optionSize = optionList.size();
                answerSize = newAnswerList.size();
                yAxis = optionSize * 60 + 85;

                for (int i = 0; i < answerSize; i++) {
                    // set option

                    TextField optionTitle = optionList.get(i).getTextField();
                    optionTitle.setText(newAnswerList.get(i).getTitle());

                    // set correct answer
                    CheckBox optionCheck = optionList.get(i).getBox();
                    optionCheck.setSelected(newAnswerList.get(i).getCorrectAnswer());

                }

            }
        });

    }

    @FXML
    private void submitQuestions() {
        try {
            System.out.println("test submitted");
            TestDao t = (TestDao) Naming.lookup("rmi://localhost/TestService");

            test.setTitle(titleField.getText());
            test.setDate(java.sql.Date.valueOf(testDate.getValue()));
            test.setStartTime(java.sql.Time.valueOf(startTime.getValue()));
            test.setEndTime(java.sql.Time.valueOf(endTime.getValue()));
            test.setLevel((Integer) levelDrop.getValue());
            test.setSemester((Integer) semesterDrop.getValue());
            test.setPassword(passwordField.getText());
            test.setDuration(Integer.parseInt(durationField.getText()));
            test.setPassMarks(Integer.parseInt(passMarksField.getText()));

            int fullMarks = 0;
            for (Question element : test.getQuestions()) {
                fullMarks += element.getMarks();
            }
            test.setFullMarks(fullMarks);

            t.saveTest(test);

        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(ViewExamController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateQuestion() {
        System.out.println("table updated");
//        questionTable.refresh();

        Question q = new Question();
        q.setTitle(questionField.getText());
        q.setMarks(Integer.parseInt(marksField.getText()));

        optionList.forEach(data -> {
            String text = data.getTextField().getText();
            System.out.println("before " + text);

            if (text != null && !text.isEmpty()) {
                System.out.println(data.getBox().isSelected());
                System.out.println("after:" + text);
                Answer a = new Answer(text, data.getBox().isSelected());
                q.addAnswer(a);
            }
        });

        ArrayList<Question> updatedQuestions = test.getQuestions();
        System.out.println(selectedItem);
        updatedQuestions.set(selectedItem, q);
        test.setQuestions(updatedQuestions);

//        questions = FXCollections.observableArrayList(updatedQuestions);
//        questionTable.refresh();
        questions = FXCollections.observableArrayList(test.getQuestions());

        questionTable.setItems(questions);

    }

    // @FXML
    // public void marksKeyReleased(KeyEvent e) {
    // String pattern = "[0-9]{1,2}";
    // Pattern pat = Pattern.compile(pattern);
    // Matcher match = pat.matcher(marksField.getText());
    // if (!match.matches()) {
    // marks.setText("Invalid marks!");
    // } else {
    // marks.setText("");
    // }
    // }
}