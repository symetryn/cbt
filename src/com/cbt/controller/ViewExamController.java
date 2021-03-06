/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import com.cbt.model.Answer;
import com.cbt.model.OptionGroup;
import com.cbt.model.Question;
import com.cbt.model.Test;
import com.cbt.dao.TestDao;
import com.cbt.utils.Router;
import com.jfoenix.controls.JFXTimePicker;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
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
    private TableColumn<Question, Number> sn;

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

    @FXML
    private Label examDurationLabel;

    @FXML
    private Label passMarksLabel;

    @FXML
    private Label testNameLabel;

    @FXML
    private Label eachMarks;

    private Test test;

    private int selectedItem;
    private Question selectedQuestion;

    ToggleGroup group;

    /**
     * To initialize the constructor
     */
    public ViewExamController() {
        test = new Test();
        optionList = new ArrayList<OptionGroup>();
        group = new ToggleGroup();
    }

    /**
     *
     * @param testId to set the testId of the test
     */
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
        RadioButton correct = new RadioButton();

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

        correct.setLayoutX(732);
        correct.setLayoutY(yAxis + 7);

        count += 1;
        yAxis += 60;

        correct.setToggleGroup(group);

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
        if (selectedQuestion.getAnswers().size() <= removedIndex) {
            selectedQuestion.getAnswers().remove(removedIndex);
        }

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

    /**
     *
     * @param contextText To show the content of the error message
     */
    public void warningMessage(String contextText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    private void saveQuestion() {
        if (questionField.getText().equals("")) {

            warningMessage("Question Feild cannot be empty!");

        } else if (eachMarks.equals("")) {

            warningMessage("Please enter the valid marks!");
        } else if (marksField.getText().equals("") || marksField.getText().equals("0")) {

            warningMessage("Please enter the valid marks");
        } else {

            Question q = new Question();
            q.setTitle(questionField.getText());
            q.setMarks(Integer.parseInt(marksField.getText()));

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
            System.out.println(test.getQuestions().size());
            setTable();

            reset();
        }
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
            if (node instanceof RadioButton) {
                ((RadioButton) node).setSelected(false);
            }

        }

    }

    /**
     * Initializes the controller class.
     *
     * @param url The url used to resolve relative paths for the root object, or
     * null if the location is not known.
     * @param rb The rb used to localize the root object, or null if the root
     * object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // initalize level dropdown
        levelDrop.getItems().removeAll();
        levelDrop.getItems().addAll(4, 5, 6);

        // initalize semester dropdown
        semesterDrop.getItems().removeAll();
        semesterDrop.getItems().addAll(1, 2);

        sn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(questionTable.getItems().indexOf(column.getValue()) + 1));

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
                System.out.println(test.getLevel());
                System.out.println(test.getSemester());

                levelDrop.getSelectionModel().select(Integer.toString(test.getLevel()));
                System.out.println(levelDrop.getSelectionModel().getSelectedItem());
                semesterDrop.getSelectionModel().select(Integer.toString(test.getSemester()));
                System.out.println(semesterDrop.getSelectionModel().getSelectedItem());
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
                selectedQuestion = newSelection;
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
                    RadioButton optionCheck = optionList.get(i).getBox();
                    optionCheck.setSelected(newAnswerList.get(i).getCorrectAnswer());

                }

            }
        });

    }

    @FXML
    private void submitQuestions() {
        LocalDate currentDate = LocalDate.now();
        LocalDate enteredDate = testDate.getValue();
        System.out.println(enteredDate);
        System.out.println(currentDate);
        if (titleField.getText().equals("") || durationField.getText().equals("") || passMarksField.getText().equals("") || passwordField.getText().equals("")) {

            warningMessage("Please complete all the feilds");

        } else if (!examDurationLabel.getText().equals("") || !testNameLabel.getText().equals("") || !passMarksLabel.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            warningMessage("Please enter the valid information!");

        } else if (levelDrop.getValue() == null || semesterDrop.getValue() == null) {

            warningMessage("Please select the valid level and semester");

        } else if (startTime.getValue() == null || endTime.getValue() == null) {
            System.out.println("Please enter valid start time and endtime");

            warningMessage("Please enter valid start time and endtime");
        } else if (testDate.getValue() == null) {

            warningMessage("Please select the exam date!");

        } else {
            try {
                System.out.println("test submitted");
                TestDao t = (TestDao) Naming.lookup("rmi://localhost/TestService");

                test.setTitle(titleField.getText());
                test.setDate(java.sql.Date.valueOf(testDate.getValue()));
                test.setStartTime(java.sql.Time.valueOf(startTime.getValue()));
                test.setEndTime(java.sql.Time.valueOf(endTime.getValue()));
                test.setLevel(Integer.parseInt(levelDrop.getValue().toString()));
                test.setSemester(Integer.parseInt(semesterDrop.getValue().toString()));
                test.setPassword(passwordField.getText());
                test.setDuration(Integer.parseInt(durationField.getText()));
                test.setPassMarks(Integer.parseInt(passMarksField.getText()));

                int fullMarks = 0;
                for (Question element : test.getQuestions()) {
                    fullMarks += element.getMarks();
                }
                test.setFullMarks(fullMarks);

                t.updateTest(test);
                Router.routeTo("Exam.fxml", "Exams");

            } catch (NotBoundException | MalformedURLException | RemoteException ex) {
                Logger.getLogger(ViewExamController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * To update the question of the Test
     */
    public void updateQuestion() {

        Question q = new Question();
        q.setId(test.getQuestions().get(selectedItem).getId());
        q.setTitle(questionField.getText());
        q.setMarks(Integer.parseInt(marksField.getText()));

        int i = 0;

        for (OptionGroup data : optionList) {
            String text = data.getTextField().getText();
            Answer a;
            int optionSize = optionList.size();
            int answerSize = selectedQuestion.getAnswers().size();
            if (optionSize > answerSize) {
                int difference = optionSize - answerSize;
                System.out.println("difference " + difference);
                for (int j = 0; j < difference; j++) {
                    selectedQuestion.addAnswer(new Answer(text, data.getBox().isSelected()));
                }
            }

            if (selectedQuestion.getAnswers().get(i).getId() == null) {
                a = new Answer(text, data.getBox().isSelected());
            } else {
                a = new Answer(selectedQuestion.getAnswers().get(i).getId(), text, data.getBox().isSelected());
            }
            System.out.println("added" + a.getId());
            q.addAnswer(a);
            i++;
        }

        ArrayList<Question> updatedQuestions = test.getQuestions();
        System.out.println(selectedItem);
        updatedQuestions.set(selectedItem, q);
        test.setQuestions(updatedQuestions);

        setTable();
        reset();

    }

    @FXML
    private void deleteQuestion(ActionEvent e) {
        if (selectedQuestion != null) {
            test.getQuestions().remove(selectedQuestion);
            questions.remove(selectedQuestion);
            setTable();

        }

    }

    /**
     *
     * To validate the test name on key release event
     */
    @FXML
    public void testNameKeyReleased(KeyEvent e) {
        String pattern = "[a-zA-Z ]{2,}";
        Pattern pat = Pattern.compile(pattern);
        Matcher match = pat.matcher(titleField.getText());
        if (!match.matches()) {
            testNameLabel.setText("Invalid test name!");
        } else {
            testNameLabel.setText("");
        }
    }

    /**
     *
     * To validate the marks on key release event
     */
    @FXML
    public void marksKeyReleased(KeyEvent e) {
        String pattern = "[0-9]{1,3}";
        Pattern pat = Pattern.compile(pattern);
        Matcher match = pat.matcher(passMarksField.getText());
        if (!match.matches()) {
            passMarksLabel.setText("Invalid marks!");
        } else {
            passMarksLabel.setText("");
        }
    }

    /**
     *
     * To validate the exam duration on key release event
     */
    @FXML
    public void examDurationKeyReleased(KeyEvent e) {
        String pattern = "[0-9]{1,3}";
        Pattern pat = Pattern.compile(pattern);
        Matcher match = pat.matcher(durationField.getText());
        if (!match.matches()) {
            examDurationLabel.setText("Invalid exam duration time!");
        } else {
            examDurationLabel.setText("");
        }
    }

    /**
     *
     * To validate the exam marks on key release event
     */
    @FXML
    public void examMarksKeyReleased(KeyEvent e) {
        String pattern = "[0-9]{1,3}";
        Pattern pat = Pattern.compile(pattern);
        Matcher match = pat.matcher(marksField.getText());
        if (!match.matches()) {
            eachMarks.setText("Invalid marks");
        } else {
            eachMarks.setText("");
        }
    }

}
