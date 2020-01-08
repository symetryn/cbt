/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import com.cbt.model.User;
import com.cbt.dao.UserDao;
import com.cbt.utils.Router;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * 
 */
public class SignUpController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField idField;

    @FXML
    private TextField lastNameField;

    @FXML
    private ComboBox levelDrop;

    @FXML
    private ComboBox semesterDrop;

    @FXML
    private TextField fileField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label firstName;

    @FXML
    private Label lastName;

    @FXML
    private Label email;

    @FXML
    private Label sid;

    @FXML
    private TextField imageUrl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initalize level dropdown
        levelDrop.getItems().removeAll(levelDrop.getItems());
        levelDrop.getItems().addAll(4, 5, 6);

        //initalize semester dropdown
        semesterDrop.getItems().removeAll(levelDrop.getItems());
        semesterDrop.getItems().addAll(1, 2);
    }

    /**
     * Initialize the signup controller
     */
    public SignUpController() {
    }

    /**
     *
     * @param contextText to accept the content of the error message
     */
    public void warningMessage(String contextText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    /**
     *
     * Validate the Signup action
     */
    public void handleSignUpClick(ActionEvent e) {

        if (firstNameField.getText().equals("") || lastNameField.getText().equals("") || emailField.getText().equals("") || idField.getText().equals("") || passwordField.getText().equals("")) {
            
            warningMessage("Please fill all the fields!");
        } else if (!firstName.getText().equals("") || !lastName.getText().equals("") || !email.getText().equals("") || !sid.getText().equals("")) {

            warningMessage("Please enter the valid information!");
        } else if (levelDrop.getValue() == null || semesterDrop.getValue() == null) {
            
            warningMessage("Please select the level and semester properly!");
        } else {
            try {
                UserDao userImpl = (UserDao) Naming.lookup("rmi://localhost/UserService");

                User user = new User();
                user.setUserID(Integer.parseInt(idField.getText()));
                user.setFirstName(firstNameField.getText());
                user.setLastName(lastNameField.getText());
                user.setEmail(emailField.getText());
                user.setImageUrl("https://source.unsplash.com/random");
                user.setPassword(passwordField.getText());
                user.setSemester((Integer) semesterDrop.getValue());
                user.setLevel((Integer) levelDrop.getValue());
                System.out.print(semesterDrop.getValue().getClass().getName());
                userImpl.registerUser(user);
                Router.routeTo("SignIn.fxml","");
            } catch (NotBoundException | MalformedURLException | RemoteException ex) {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);

                if (ex.getCause().getMessage().startsWith("com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException")) {
                    
                    warningMessage("User already exists");
                }
//                System.out.println(ex instanceof MySQLIntegrityConstraintViolationException);
//                if (ex.getCause().getClass().equals(MySQLIntegrityConstraintViolationException.class)) {
//                    Alert msg = new Alert(Alert.AlertType.ERROR, "Email and ID must be unique", ButtonType.OK);
//                    msg.show();
//                }
            }

        }

    }

    /**
     *
     * route to signin page
     */
    @FXML
    public void handleSignInClick(ActionEvent e) {

        Router.routeTo("SignIn.fxml","");

    }

    /**
     *
     * Validation of the first name on key release event
     */
    @FXML
    public void firstNameKeyReleased(KeyEvent e) {
        String pattern = "[a-zA-Z]{2,}";
        Pattern pat = Pattern.compile(pattern);
        Matcher match = pat.matcher(firstNameField.getText());
        if (!match.matches()) {
            firstName.setText("Invalid first name!");
        } else {
            firstName.setText("");
        }
    }

    /**
     *
     * Validation of the last name on key release event
     */
    @FXML
    public void lastNameKeyReleased(KeyEvent e) {
        String pattern = "[a-zA-Z]{2,}";
        Pattern pat = Pattern.compile(pattern);
        Matcher match = pat.matcher(lastNameField.getText());
        if (!match.matches()) {
            lastName.setText("Invalid last name!");
        } else {
            lastName.setText("");
        }
    }

    /**
     *
     * Validation of the email on key release event
     */
    @FXML
    public void emailKeyReleased(KeyEvent e) {
        String pattern = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
        Pattern pat = Pattern.compile(pattern);
        Matcher match = pat.matcher(emailField.getText());
        if (!match.matches()) {
            email.setText("Invalid email!");
        } else {
            email.setText("");
        }
    }

    /**
     *
     * Validation of the student id on key release event
     */
    @FXML
    public void sidKeyReleased(KeyEvent e) {
        String pattern = "[0-9]{7}";
        Pattern pat = Pattern.compile(pattern);
        Matcher match = pat.matcher(idField.getText());
        if (!match.matches()) {
            sid.setText("Invalid student ID!");
        } else {
            sid.setText("");
        }
    }


}
