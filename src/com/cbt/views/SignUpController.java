/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.views;

import com.cbt.bll.User;
import com.cbt.dao.UserDao;
import com.cbt.utils.Router;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SignUpController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Router router;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //initalize level dropdown
    levelDrop.getItems().removeAll(levelDrop.getItems());
    levelDrop.getItems().addAll(4,5,6);
    
    //initalize semester dropdown
    semesterDrop.getItems().removeAll(levelDrop.getItems());
    semesterDrop.getItems().addAll(1,2);
    }

    public SignUpController() {
    
        router = new Router();
    }

    public void handleSignUpClick(ActionEvent e) {

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
        } catch (NotBoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void handleSignInClick(ActionEvent e) {

        router.routeTo("SignIn.fxml", e);

//        try {
//            UserDao userImpl = (UserDao) Naming.lookup("rmi://localhost/UserService");
//
//            User user = new User();
//            user.setFirstName("rojan");
//            userImpl.registerUser(user);
//
//        } catch (NotBoundException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (RemoteException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
